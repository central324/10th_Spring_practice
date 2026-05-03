package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;

public interface ReviewService {

    ReviewResDTO.CreateReviewResultDTO createReview(Long storeId, ReviewReqDTO.CreateReviewDTO request);
}
