package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionResDTO;

public interface MissionService {

    MissionResDTO.HomeResultDTO getHomeMissions();

    MissionResDTO.AvailableMissionListDTO getAvailableMissions();

    MissionResDTO.MyMissionListDTO getMyMissions();

    MissionResDTO.ChallengeResultDTO challengeMission(Long missionId);

    MissionResDTO.CompleteResultDTO completeMission(Long missionId);
}
