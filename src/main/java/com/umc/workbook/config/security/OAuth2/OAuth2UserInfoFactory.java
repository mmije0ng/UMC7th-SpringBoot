package com.umc.workbook.config.security.OAuth2;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(String provider, Map<String, Object> attributes) {
        switch (provider) {
            case "google":
                return new GoogleUserDetails(attributes);
            case "kakao":
                return new KakaoUserDetails(attributes);
            default:
                throw new IllegalArgumentException("지원하지 않는 OAuth2 공급자: " + provider);
        }
    }
}