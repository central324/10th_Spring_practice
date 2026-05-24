package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class ReviewReqDTO {

    @Getter
    public static class CreateReviewDTO {

        @NotNull(message = "score는 필수입니다.")
        @Min(value = 0, message = "score는 0 이상이어야 합니다.")
        @Max(value = 5, message = "score는 5 이하여야 합니다.")
        private Float score;

        @NotBlank(message = "body는 필수입니다.")
        private String body;
    }
}
