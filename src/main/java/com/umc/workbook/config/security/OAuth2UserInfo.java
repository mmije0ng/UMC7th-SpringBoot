package com.umc.workbook.config.security;

public interface OAuth2UserInfo {
    String getProvider();      // OAuth2 공급자 (e.g., kakao, google)
    String getProviderId();    // 공급자에서 제공한 고유 ID
    String getEmail();         // 사용자 이메일
    String getName();          // 사용자 이름 또는 닉네임
}
