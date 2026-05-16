package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_1", "미션을 찾을 수 없습니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_2", "해당 가게를 찾을 수 없습니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_3", "회원을 찾을 수 없습니다."),
    MISSION_ALREADY_CHALLENGING(HttpStatus.BAD_REQUEST, "MISSION400_1", "이미 도전 중인 미션입니다."),
    MISSION_ALREADY_COMPLETED(HttpStatus.BAD_REQUEST, "MISSION400_2", "이미 완료한 미션입니다."),
    MISSION_NOT_CHALLENGING(HttpStatus.BAD_REQUEST, "MISSION400_3", "도전 중인 미션이 아닙니다."),
    INVALID_MISSION_STATUS(HttpStatus.BAD_REQUEST, "MISSION400_4", "미션 상태값이 올바르지 않습니다."),
    MISSION_FORBIDDEN(HttpStatus.FORBIDDEN, "MISSION403_1", "해당 미션에 대한 권한이 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
