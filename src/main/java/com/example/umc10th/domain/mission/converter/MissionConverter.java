package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionConverter {

    public static MissionResDTO.HomeResultDTO toHomeResultDTO() {
        return MissionResDTO.HomeResultDTO.builder()
                .ongoingMissionCount(2)
                .completedMissionCount(5)
                .missions(List.of(
                        MissionResDTO.MissionPreviewDTO.builder()
                                .missionId(1L)
                                .storeName("버거킹")
                                .title("햄버거 최대 몇 개 드세요 ?")
                                .rewardPoint(500)
                                .build(),
                        MissionResDTO.MissionPreviewDTO.builder()
                                .missionId(2L)
                                .storeName("컴포즈")
                                .title("뜨아 얼마나 빨리 드세요 ?")
                                .rewardPoint(300)
                                .build()
                ))
                .build();
    }

    public static MissionResDTO.AvailableMissionListDTO toAvailableMissionListDTO() {
        return MissionResDTO.AvailableMissionListDTO.builder()
                .missions(List.of(
                        MissionResDTO.AvailableMissionDTO.builder()
                                .missionId(1L)
                                .storeName("버거킹")
                                .title("햄버거 최대 몇 개 드세요 ?")
                                .description("와퍼 단품 3개 이상 먹기. 성공 시 포인트 적립")
                                .rewardPoint(500)
                                .deadline(LocalDate.now().plusDays(7))
                                .build(),
                        MissionResDTO.AvailableMissionDTO.builder()
                                .missionId(2L)
                                .storeName("컴포즈")
                                .title("뜨아 얼마나 빨리 드세요 ?")
                                .description("아메리카노 HOT 한 잔 원샷하기. 성공 시 포인트 적립")
                                .rewardPoint(300)
                                .deadline(LocalDate.now().plusDays(5))
                                .build()
                ))
                .build();
    }

    public static MissionResDTO.MyMissionListDTO toMyMissionListDTO() {
        return MissionResDTO.MyMissionListDTO.builder()
                .missions(List.of(
                        MissionResDTO.MyMissionDTO.builder()
                                .missionId(1L)
                                .storeName("버거킹")
                                .title("햄버거 최대 몇 개 드세요 ?")
                                .status("CHALLENGING")
                                .rewardPoint(500)
                                .build(),
                        MissionResDTO.MyMissionDTO.builder()
                                .missionId(2L)
                                .storeName("컴포즈")
                                .title("뜨아 얼마나 빨리 드세요 ?")
                                .status("COMPLETED")
                                .rewardPoint(300)
                                .build()
                ))
                .build();
    }

    public static MissionResDTO.ChallengeResultDTO toChallengeResultDTO(Long missionId) {
        return MissionResDTO.ChallengeResultDTO.builder()
                .missionId(missionId)
                .status("CHALLENGING")
                .challengedAt(LocalDateTime.now())
                .build();
    }

    public static MissionResDTO.CompleteResultDTO toCompleteResultDTO(Long missionId) {
        return MissionResDTO.CompleteResultDTO.builder()
                .missionId(missionId)
                .status("COMPLETED")
                .completedAt(LocalDateTime.now())
                .build();
    }
}
