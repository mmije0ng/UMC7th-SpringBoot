package com.umc.workbook.config.security;

import com.umc.workbook.domain.Member;
import com.umc.workbook.domain.enums.Gender;
import com.umc.workbook.domain.enums.Role;
import com.umc.workbook.repository.MemberRepository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService { // 커카오 로그인 후 받은 사용자 정보를 처리

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 카카오에서 제공하는 사용자 정보를 OAuth2User 객체로 받아옴
        // 객체의 attributes에는 사용자의 닉네임 등 기본 정보가 포함
        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String, Object> attributes = oAuth2User.getAttributes();

        String provider = userRequest.getClientRegistration().getRegistrationId();

        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(provider, attributes);
        log.info("소셜 로그인 이메일: {}, 닉네임: {}", oAuth2UserInfo.getEmail(), oAuth2UserInfo.getName());

        // 사용자 정보 저장 또는 업데이트
        Member member = saveOrUpdateUser(oAuth2UserInfo.getEmail(), oAuth2UserInfo.getName());
        log.info("로그인된 Member memberId: {}, 닉네임: {}, 이메일: {}", member.getId(), member.getNickName(), member.getEmail());

        // 이메일을 Principal로 사용하기 위해 attributes 수정
        Map<String, Object> modifiedAttributes = new HashMap<>(attributes);
        modifiedAttributes.put("email", oAuth2UserInfo.getEmail());

        // DefaultOAuth2User 객체를 생성하여 반환
        return new DefaultOAuth2User(
                oAuth2User.getAuthorities(), // 권한
                modifiedAttributes, // 속성
                "email"  // email Principal로 설정 (주요 식별자 email 설정)
        );
    }

    // 사용자 정보 저장 및 업데이트
    // null 일 경우 orElseGet 이 실행되어 멤버 저장
    private Member saveOrUpdateUser(String email, String nickname) {
        return memberRepository.findByEmail(email)
                .orElseGet(() -> memberRepository.save(Member.builder()
                        .email(email)
                        .nickName(nickname)
                        .loginPassword(passwordEncoder.encode("OAUTH_USER_" + UUID.randomUUID()))
                        .gender(Gender.NONE)
                        .memberAddress("소셜로그인")
                        .role(Role.USER)
                        .build()));
    }
}