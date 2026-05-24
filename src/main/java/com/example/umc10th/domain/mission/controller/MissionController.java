package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/api/missions/home")
    public ApiResponse<MissionResDTO.HomeResultDTO> getHomeMissions(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam Long regionId
    )
    {
        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_DETAIL_FOUND,
                missionService.getHomeMissions(userDetails.getMember().getId(), regionId)

        );
    }

    @SecurityRequirement(name = "bearerAuth")
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

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/api/users/me/missions/progress")
    public ApiResponse<MissionResDTO.MyMissionListDTO> getMyProgressMissions(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_CHALLENGE_LIST_FOUND,
                missionService.getMyProgressMissions(
                        userDetails.getMember().getId(),
                        pageable
                )
        );
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/api/missions/{missionId}/challenges")
    public ApiResponse<MissionResDTO.ChallengeResultDTO> challengeMission(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long missionId
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_CHALLENGE_CREATED,
                missionService.challengeMission(userDetails.getMember().getId(), missionId)
        );
    }

    @SecurityRequirement(name = "bearerAuth")
    @PatchMapping("/api/missions/{missionId}/complete")
    public ApiResponse<MissionResDTO.CompleteResultDTO> completeMission(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long missionId
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_UPDATED,
                missionService.completeMission(userDetails.getMember().getId(), missionId)
        );
    }
}
