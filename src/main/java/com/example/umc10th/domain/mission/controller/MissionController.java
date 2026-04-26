package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/api/missions/home")
    public ApiResponse<MissionResDTO.HomeResultDTO> getHomeMissions() {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionService.getHomeMissions()
        );
    }

    @GetMapping("/api/missions/available")
    public ApiResponse<MissionResDTO.AvailableMissionListDTO> getAvailableMissions() {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionService.getAvailableMissions()
        );
    }

    @GetMapping("/api/users/me/missions")
    public ApiResponse<MissionResDTO.MyMissionListDTO> getMyMissions() {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionService.getMyMissions()
        );
    }

    @PostMapping("/api/missions/{missionId}/challenges")
    public ApiResponse<MissionResDTO.ChallengeResultDTO> challengeMission(
            @PathVariable Long missionId
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.CREATED,
                missionService.challengeMission(missionId)
        );
    }

    @PatchMapping("/api/missions/{missionId}/complete")
    public ApiResponse<MissionResDTO.CompleteResultDTO> completeMission(
            @PathVariable Long missionId
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionService.completeMission(missionId)
        );
    }
}
