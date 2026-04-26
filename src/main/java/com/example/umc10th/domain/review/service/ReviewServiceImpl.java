package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Override
    public ReviewResDTO.CreateReviewResultDTO createReview(Long storeId, ReviewReqDTO.CreateReviewDTO request) {
        Long reviewId = 1L; // 임시 더미 ID
        return ReviewConverter.toCreateReviewResultDTO(reviewId, storeId, request);
    }
}
