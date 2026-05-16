package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.enums.ReviewSortType;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.security.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/api/stores/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReviewResultDTO> createReview(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long storeId,
            @RequestBody @Valid ReviewReqDTO.CreateReviewDTO request
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.REVIEW_CREATED,
                reviewService.createReview(userDetails.getMember().getId(), storeId, request)
        );
    }

    @GetMapping("/api/users/me/reviews")
    public ApiResponse<ReviewResDTO.MyReviewCursorPageDTO> getMyReviews(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam(defaultValue = "ID") ReviewSortType sort,
            @RequestParam(required = false) Long cursorId,
            @RequestParam(required = false) Float cursorScore,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.REVIEW_LIST_FOUND,
                reviewService.getMyReviews(
                        userDetails.getMember().getId(),
                        sort,
                        cursorId,
                        cursorScore,
                        size
                )
        );
    }
}
