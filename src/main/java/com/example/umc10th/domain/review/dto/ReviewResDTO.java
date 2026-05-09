package com.example.umc10th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

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

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyReviewPreviewDTO {
        private Long reviewId;
        private Long storeId;
        private String storeName;
        private Float score;
        private String body;
        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyReviewCursorPageDTO {
        private List<MyReviewPreviewDTO> reviewList;
        private Integer listSize;
        private Boolean hasNext;
        private Long nextCursorId;
        private Float nextCursorScore;
    }
}
