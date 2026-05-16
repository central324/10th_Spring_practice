package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    // MISSION_CREATED(HttpStatus.CREATED, "MISSION201_1", "미션 생성에 성공했습니다."),
    MISSION_UPDATED(HttpStatus.OK, "MISSION200_1", "미션을 완료했습니다."),
    // MISSION_DELETED(HttpStatus.OK, "MISSION200_2", "미션 삭제에 성공했습니다."),
    MISSION_LIST_FOUND(HttpStatus.OK, "MISSION200_3", "도전 가능한 미션 목록 조회에 성공했습니다."),
    MISSION_DETAIL_FOUND(HttpStatus.OK, "MISSION200_4", "미션 상세 조회에 성공했습니다."),
    MISSION_CHALLENGE_CREATED(HttpStatus.CREATED, "MISSION201_2", "미션 도전을 시작했습니다."),
    MISSION_CHALLENGE_LIST_FOUND(HttpStatus.OK, "MISSION200_5", "도전 중인 미션 조회에 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
