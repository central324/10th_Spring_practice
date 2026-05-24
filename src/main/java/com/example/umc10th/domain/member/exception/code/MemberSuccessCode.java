package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    SIGNUP_SUCCESS(HttpStatus.CREATED, "MEMBER201_1", "회원가입에 성공했습니다."),
    LOGIN_SUCCESS(HttpStatus.OK, "MEMBER200_1", "로그인에 성공했습니다."),
    MYPAGE_SUCCESS(HttpStatus.OK, "MEMBER2003", "마이페이지 조회 성공");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
