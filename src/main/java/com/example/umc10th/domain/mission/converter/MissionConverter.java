package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.UserMission;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public class MissionConverter {

    public static MissionResDTO.MissionPreviewDTO toMissionPreviewDTO(Mission mission) {
        return MissionResDTO.MissionPreviewDTO.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .title(mission.getMissionSpec())
                .rewardPoint(mission.getReward())
                .build();
    }

    public static MissionResDTO.HomeResultDTO toHomeResultDTO(
            Integer ongoingMissionCount,
            Integer completedMissionCount,
            List<Mission> missionList
    ) {
        return MissionResDTO.HomeResultDTO.builder()
                .ongoingMissionCount(ongoingMissionCount)
                .completedMissionCount(completedMissionCount)
                .missions(missionList.stream()
                        .map(MissionConverter::toMissionPreviewDTO)
                        .toList())
                .build();
    }

    public static MissionResDTO.AvailableMissionDTO toAvailableMissionDTO(Mission mission) {
        return MissionResDTO.AvailableMissionDTO.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .title(mission.getMissionSpec())
                .description(mission.getMissionSpec())
                .rewardPoint(mission.getReward())
                .deadline(mission.getDeadline().toLocalDate())
                .build();
    }

    public static MissionResDTO.AvailableMissionListDTO toAvailableMissionListDTO(Page<Mission> missionPage) {
        return MissionResDTO.AvailableMissionListDTO.builder()
                .missions(missionPage.getContent().stream()
                        .map(MissionConverter::toAvailableMissionDTO)
                        .toList())
                .listSize(missionPage.getNumberOfElements())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }

    public static MissionResDTO.MyMissionDTO toMyMissionDTO(UserMission userMission) {
        return MissionResDTO.MyMissionDTO.builder()
                .missionId(userMission.getMission().getId())
                .storeName(userMission.getMission().getStore().getName())
                .title(userMission.getMission().getMissionSpec())
                .status(userMission.getChallengingStatus() ? "CHALLENGING" : "COMPLETED")
                .rewardPoint(userMission.getMission().getReward())
                .build();
    }

    public static MissionResDTO.MyMissionListDTO toMyMissionListDTO(Page<UserMission> userMissionPage) {
        return MissionResDTO.MyMissionListDTO.builder()
                .missions(userMissionPage.getContent().stream()
                        .map(MissionConverter::toMyMissionDTO)
                        .toList())
                .listSize(userMissionPage.getNumberOfElements())
                .totalPage(userMissionPage.getTotalPages())
                .totalElements(userMissionPage.getTotalElements())
                .isFirst(userMissionPage.isFirst())
                .isLast(userMissionPage.isLast())
                .build();
    }

    public static MissionResDTO.ChallengeResultDTO toChallengeResultDTO(UserMission userMission) {
        return MissionResDTO.ChallengeResultDTO.builder()
                .missionId(userMission.getMission().getId())
                .status("CHALLENGING")
                .challengedAt(userMission.getCreatedAt())
                .build();
    }

    public static MissionResDTO.CompleteResultDTO toCompleteResultDTO(UserMission userMission) {
        return MissionResDTO.CompleteResultDTO.builder()
                .missionId(userMission.getMission().getId())
                .status("COMPLETED")
                .completedAt(LocalDateTime.now())
                .build();
    }
}
