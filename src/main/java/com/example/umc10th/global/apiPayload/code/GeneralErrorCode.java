package com.example.umc10th.global.apiPayload.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GeneralErrorCode implements BaseErrorCode {

    BAD_REQUEST(400, "COMMON400", "잘못된 요청입니다."),
    UNAUTHORIZED(401, "COMMON401", "인증이 필요합니다."),
    FORBIDDEN(403, "COMMON403", "금지된 요청입니다."),
    NOT_FOUND(404, "COMMON404", "대상을 찾을 수 없습니다."),
    METHOD_NOT_ALLOWED(405, "COMMON405", "지원하지 않는 HTTP 메서드입니다."),
    INTERNAL_SERVER_ERROR(500, "COMMON500", "서버 내부 오류입니다."),


    VALIDATION_ERROR(400, "COMMON4001", "입력값 검증에 실패했습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
