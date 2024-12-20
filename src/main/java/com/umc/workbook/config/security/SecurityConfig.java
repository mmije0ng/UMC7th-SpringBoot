package com.umc.workbook.config.security;

import com.umc.workbook.config.security.OAuth2.GoogleUserDetails;
import com.umc.workbook.config.security.OAuth2.KakaoUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정 활성화
@Configuration
public class SecurityConfig { // 애플리케이션의 보안 정책을 정의
    private final CustomUserDetailsService customUserDetailsService; // CustomUserDetailsService 정의

    // SecurityFilterChain 정의
    // HttpSecurity 객체를 통해 보안 설정 구성
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // HTTP 요청에 대한 접근 제어 설정
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home", "/signup", "/members/signup", "/api/auth/signup", "/css/**").permitAll() // 인증 없이 접근 가능한 경로 지정
                        .requestMatchers("/admin/**").hasRole("ADMIN") // 관리자만 접근 가능
                        .anyRequest().authenticated() // 그 외의 모든 요청에 대한 인증 요청
                )

                // 폼 기반 로그인 설정
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true) // 로그인 성공시 리다이렉트
                        .permitAll() // 모든 사용자 접근 가능
                )

                // 소셜 로그인
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .permitAll()
                )

                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout") // 로그아웃 성공 시 리다이렉트
                        .permitAll()
                );

        return http.build();
    }

    // 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}