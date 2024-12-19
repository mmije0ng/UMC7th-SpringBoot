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

        log.info("로그인된 Member 아이디: {}, 닉네임: {}, 이메일: {}", member.getId(), member.getNickName(), username);

        return org.springframework.security.core.userdetails.User
                .withUsername(member.getEmail())
                .password(member.getLoginPassword())
                .roles(member.getRole().name())
                .build();
    }
}
