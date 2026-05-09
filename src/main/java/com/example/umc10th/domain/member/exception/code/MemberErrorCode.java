package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "사용자를 찾을 수 없습니다."),
    EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "MEMBER400_1", "이미 사용 중인 이메일입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "MEMBER400_2", "비밀번호가 일치하지 않습니다."),
    DEFAULT_IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_2", "기본 프로필 이미지를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}

