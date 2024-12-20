package com.umc.workbook.config.security.OAuth2;

import java.util.Map;

public class KakaoUserDetails implements OAuth2UserInfo {
    private final Map<String, Object> attributes;
    private final Map<String, Object> properties;

    public KakaoUserDetails(Map<String, Object> attributes) {
        this.attributes = attributes;
        this.properties = (Map<String, Object>) attributes.get("properties");
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {
        return String.valueOf(attributes.get("id"));
    }

    @Override
    public String getEmail() {
        return (String) properties.get("nickname") + "@kakao.com";
    }

    @Override
    public String getName() {
        return (String) properties.get("nickname");
    }
}
