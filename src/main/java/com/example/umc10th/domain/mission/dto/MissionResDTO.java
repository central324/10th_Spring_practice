package com.example.umc10th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MissionPreviewDTO {
        private Long missionId;
        private String storeName;
        private String title;
        private Integer rewardPoint;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class HomeResultDTO {
        private Integer ongoingMissionCount;
        private Integer completedMissionCount;
        private List<MissionPreviewDTO> missions;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class AvailableMissionDTO {
        private Long missionId;
        private String storeName;
        private String title;
        private String description;
        private Integer rewardPoint;
        private LocalDate deadline;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class AvailableMissionListDTO {
        private List<AvailableMissionDTO> missions;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyMissionDTO {
        private Long missionId;
        private String storeName;
        private String title;
        private String status;
        private Integer rewardPoint;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyMissionListDTO {
        private List<MyMissionDTO> missions;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class ChallengeResultDTO {
        private Long missionId;
        private String status;
        private LocalDateTime challengedAt;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class CompleteResultDTO {
        private Long missionId;
        private String status;
        private LocalDateTime completedAt;
    }
}
