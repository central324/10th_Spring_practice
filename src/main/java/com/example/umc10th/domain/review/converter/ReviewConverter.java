package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;

import java.util.List;

public class ReviewConverter {

    public static ReviewResDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review) {
        return ReviewResDTO.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .score(review.getRate())
                .body(review.getReview_contents())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.MyReviewPreviewDTO toMyReviewPreviewDTO(Review review) {
        return ReviewResDTO.MyReviewPreviewDTO.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .storeName(review.getStore().getName())
                .score(review.getRate())
                .body(review.getReview_contents())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.MyReviewCursorPageDTO toMyReviewCursorPageDTO(
            List<Review> reviewList,
            Boolean hasNext,
            Long nextCursorId,
            Float nextCursorScore
    ) {
        List<ReviewResDTO.MyReviewPreviewDTO> previewList = reviewList.stream()
                .map(ReviewConverter::toMyReviewPreviewDTO)
                .toList();

        return ReviewResDTO.MyReviewCursorPageDTO.builder()
                .reviewList(previewList)
                .listSize(previewList.size())
                .hasNext(hasNext)
                .nextCursorId(nextCursorId)
                .nextCursorScore(nextCursorScore)
                .build();
    }
}
