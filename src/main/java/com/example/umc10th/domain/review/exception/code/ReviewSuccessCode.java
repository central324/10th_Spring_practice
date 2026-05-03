package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    REVIEW_CREATED(HttpStatus.CREATED, "REVIEW201_1", "리뷰 작성에 성공했습니다."),
    REVIEW_UPDATED(HttpStatus.OK, "REVIEW200_1", "리뷰 수정에 성공했습니다."),
    REVIEW_DELETED(HttpStatus.OK, "REVIEW200_2", "리뷰 삭제에 성공했습니다."),
    REVIEW_LIST_FOUND(HttpStatus.OK, "REVIEW200_3", "리뷰 목록 조회에 성공했습니다."),
    REVIEW_DETAIL_FOUND(HttpStatus.OK, "REVIEW200_4", "리뷰 상세 조회에 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
