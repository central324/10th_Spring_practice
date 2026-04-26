package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;

import java.time.LocalDateTime;

public class ReviewConverter {

    public static ReviewResDTO.CreateReviewResultDTO toCreateReviewResultDTO(
            Long reviewId,
            Long storeId,
            ReviewReqDTO.CreateReviewDTO request
    ) {
        return ReviewResDTO.CreateReviewResultDTO.builder()
                .reviewId(reviewId)
                .storeId(storeId)
                .score(request.getScore())
                .body(request.getBody())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
