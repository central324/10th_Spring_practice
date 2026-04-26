package com.example.umc10th.global.apiPayload.code;

public interface BaseErrorCode {
    String getCode();
    String getMessage();
    int getHttpStatus();
}
