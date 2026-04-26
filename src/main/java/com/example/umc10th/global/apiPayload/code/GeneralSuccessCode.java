package com.example.umc10th.global.apiPayload.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    OK(200, "COMMON200", "요청에 성공했습니다."),
    CREATED(201, "COMMON201", "생성에 성공했습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}