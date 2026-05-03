package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Override
    public ReviewResDTO.CreateReviewResultDTO createReview(Long storeId, ReviewReqDTO.CreateReviewDTO request) {
        validateStoreId(storeId);
        validateReviewRequest(request);

        Long reviewId = 1L; // 임시 더미 ID
        return ReviewConverter.toCreateReviewResultDTO(reviewId, storeId, request);
    }

    private void validateStoreId(Long storeId) {
        if (storeId == null || storeId <= 0) {
            throw new ProjectException(ReviewErrorCode.STORE_NOT_FOUND);
        }
    }

    private void validateReviewRequest(ReviewReqDTO.CreateReviewDTO request) {
        if (request == null || request.getScore() == null) {
            throw new ProjectException(ReviewErrorCode.INVALID_REVIEW_SCORE);
        }

        if (request.getScore() < 1 || request.getScore() > 5) {
            throw new ProjectException(ReviewErrorCode.INVALID_REVIEW_SCORE);
        }
    }
}
