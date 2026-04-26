package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import org.springframework.stereotype.Service;

@Service
public class MissionServiceImpl implements MissionService {

    @Override
    public MissionResDTO.HomeResultDTO getHomeMissions() {
        return MissionConverter.toHomeResultDTO();
    }

    @Override
    public MissionResDTO.AvailableMissionListDTO getAvailableMissions() {
        return MissionConverter.toAvailableMissionListDTO();
    }

    @Override
    public MissionResDTO.MyMissionListDTO getMyMissions() {
        return MissionConverter.toMyMissionListDTO();
    }

    @Override
    public MissionResDTO.ChallengeResultDTO challengeMission(Long missionId) {
        return MissionConverter.toChallengeResultDTO(missionId);
    }

    @Override
    public MissionResDTO.CompleteResultDTO completeMission(Long missionId) {
        return MissionConverter.toCompleteResultDTO(missionId);
    }
}
