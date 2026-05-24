package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.enums.ReviewSortType;

public interface ReviewService {

    ReviewResDTO.CreateReviewResultDTO createReview(Long memberId, Long storeId, ReviewReqDTO.CreateReviewDTO request);

    ReviewResDTO.MyReviewCursorPageDTO getMyReviews(
            Long memberId,
            ReviewSortType sort,
            Long cursorId,
            Float cursorScore,
            Integer size
    );
}
