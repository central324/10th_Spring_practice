package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionResDTO;

public interface MissionService {

    MissionResDTO.HomeResultDTO getHomeMissions(Long memberId, Long regionId);

    MissionResDTO.AvailableMissionListDTO getAvailableMissions(Long regionId, Integer page, Integer size);

    MissionResDTO.MyMissionListDTO getMyMissions(Long memberId, String status, Integer page, Integer size);

    MissionResDTO.ChallengeResultDTO challengeMission(Long memberId, Long missionId);

    MissionResDTO.CompleteResultDTO completeMission(Long memberId, Long missionId);
}
