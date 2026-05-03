package com.example.umc10th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ReviewResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class CreateReviewResultDTO {
        private Long reviewId;
        private Long storeId;
        private Float score;
        private String body;
        private LocalDateTime createdAt;
    }
}
