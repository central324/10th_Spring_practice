package com.example.umc10th.global.security.oauth;

import lombok.Getter;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

@Getter
public class CustomOAuth2User implements OAuth2User {

    private final String socialId;
    private final String email;
    private final String nickname;
    private final Map<String, Object> attributes;

    public CustomOAuth2User(String socialId, String email, String nickname, Map<String, Object> attributes) {
        this.socialId = socialId;
        this.email = email;
        this.nickname = nickname;
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public java.util.Collection<? extends org.springframework.security.core.GrantedAuthority> getAuthorities() {
        return java.util.List.of(() -> "ROLE_USER");
    }

    @Override
    public String getName() {
        return socialId;
    }
}
