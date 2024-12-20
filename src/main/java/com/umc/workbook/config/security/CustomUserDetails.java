package com.umc.workbook.config.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails, OAuth2User {
    private final CustomUserInfoDTO customUser;
    private Map <String, Object> attributes;

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> roles = new ArrayList<>();
        roles.add(customUser.getRole());

        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return customUser.getPassword();
    }

    @Override
    public String getUsername() {
        return customUser.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() { // 계정이 만료되지 않았는지 확인
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { // 계정이 잠기지 않았는지 확인
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { // 비밀번호(자격 증명)가 만료되지 않았는지 확인
        return true;
    }

    @Override
    public boolean isEnabled() { // 계정이 활성화(Enabled) 상태인지 확인
        return true;
    }

    @Override
    public String getName() {
        return null;
    }
}
