package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/api/missions/home")
    public ApiResponse<MissionResDTO.HomeResultDTO> getHomeMissions(
            @RequestParam Long memberId,
            @RequestParam Long regionId
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_DETAIL_FOUND,
                missionService.getHomeMissions(memberId, regionId)
        );
    }

    @GetMapping("/api/missions/available")
    public ApiResponse<MissionResDTO.AvailableMissionListDTO> getAvailableMissions(
            @RequestParam Long regionId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_LIST_FOUND,
                missionService.getAvailableMissions(regionId, page, size)
        );
    }

    @GetMapping("/api/users/me/missions")
    public ApiResponse<MissionResDTO.MyMissionListDTO> getMyMissions(
            @RequestParam Long memberId,
            @RequestParam String status,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_CHALLENGE_LIST_FOUND,
                missionService.getMyMissions(memberId, status, page, size)
        );
    }

    @PostMapping("/api/missions/{missionId}/challenges")
    public ApiResponse<MissionResDTO.ChallengeResultDTO> challengeMission(
            @RequestParam Long memberId,
            @PathVariable Long missionId
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_CHALLENGE_CREATED,
                missionService.challengeMission(memberId, missionId)
        );
    }

    @PatchMapping("/api/missions/{missionId}/complete")
    public ApiResponse<MissionResDTO.CompleteResultDTO> completeMission(
            @RequestParam Long memberId,
            @PathVariable Long missionId
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_UPDATED,
                missionService.completeMission(memberId, missionId)
        );
    }
}
