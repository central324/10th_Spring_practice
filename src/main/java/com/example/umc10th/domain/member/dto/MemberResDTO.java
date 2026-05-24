package com.example.umc10th.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MemberResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class SignupResultDTO {
        private Long userId;
        private String name;
        private String email;
        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginResultDTO {
        private Long userId;
        private String name;
        private String email;
        private String accessToken;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyPageDTO {
        private String nickname;
        private String email;
        private String phoneNumber;
        private Integer point;
        private String profileImageUrl;
    }
}
