package com.umc.workbook.config.security;

import com.umc.workbook.domain.Member;
import com.umc.workbook.repository.MemberRepository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 이메일을 가진 사용자가 존재하지 않습니다. " + username));

        CustomUserInfoDTO dto = CustomUserInfoDTO.builder()
                .memberId(member.getId())
                .role(member.getRole())
                .email(member.getEmail())
                .password(member.getLoginPassword())
                .nickName(member.getNickName())
                .build();

        log.info("로그인된 Member memberId: {}, 닉네임: {}, 이메일: {}", member.getId(), member.getNickName(), username);

        return new CustomUserDetails(dto);
    }
}
