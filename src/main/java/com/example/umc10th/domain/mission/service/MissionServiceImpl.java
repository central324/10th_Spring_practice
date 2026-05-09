package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.UserMission;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.UserMissionRepository;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public MissionResDTO.HomeResultDTO getHomeMissions(Long memberId, Long regionId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ProjectException(MemberErrorCode.MEMBER_NOT_FOUND));

        int ongoingMissionCount = (int) userMissionRepository.countByMember_IdAndChallengingStatus(member.getId(), true);
        int completedMissionCount = (int) userMissionRepository.countByMember_IdAndChallengingStatus(member.getId(), false);

        List<Mission> missionList = missionRepository.findTopMissionsByRegionId(regionId, PageRequest.of(0, 3));

        return MissionConverter.toHomeResultDTO(ongoingMissionCount, completedMissionCount, missionList);
    }

    @Override
    @Transactional(readOnly = true)
    public MissionResDTO.AvailableMissionListDTO getAvailableMissions(Long regionId, Integer page, Integer size) {
        Page<Mission> missionPage = missionRepository.findAvailableMissionsByRegionId(
                regionId,
                PageRequest.of(page, size)
        );

        return MissionConverter.toAvailableMissionListDTO(missionPage);
    }

    @Override
    @Transactional(readOnly = true)
    public MissionResDTO.MyMissionListDTO getMyProgressMissions(Long memberId, Integer page, Integer size) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new ProjectException(MemberErrorCode.MEMBER_NOT_FOUND));

        Page<UserMission> userMissionPage = userMissionRepository.findMyMissionsByStatus(
                memberId,
                true,
                PageRequest.of(page, size)
        );

        return MissionConverter.toMyMissionListDTO(userMissionPage);
    }

    @Override
    public MissionResDTO.ChallengeResultDTO challengeMission(Long memberId, Long missionId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ProjectException(MemberErrorCode.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new ProjectException(MissionErrorCode.MISSION_NOT_FOUND));

        if (userMissionRepository.existsByMember_IdAndMission_IdAndChallengingStatus(memberId, missionId, true)) {
            throw new ProjectException(MissionErrorCode.MISSION_ALREADY_CHALLENGING);
        }

        UserMission userMission = UserMission.builder()
                .member(member)
                .mission(mission)
                .challengingStatus(true)
                .createdAt(LocalDateTime.now())
                .build();

        UserMission savedUserMission = userMissionRepository.save(userMission);

        return MissionConverter.toChallengeResultDTO(savedUserMission);
    }

    @Override
    @Transactional
    public MissionResDTO.CompleteResultDTO completeMission(Long memberId, Long missionId) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new ProjectException(MemberErrorCode.MEMBER_NOT_FOUND));

        missionRepository.findById(missionId)
                .orElseThrow(() -> new ProjectException(MissionErrorCode.MISSION_NOT_FOUND));

        UserMission userMission = userMissionRepository.findByMemberIdAndMissionId(memberId, missionId)
                .orElseThrow(() -> new ProjectException(MissionErrorCode.MISSION_NOT_FOUND));

        if (!userMission.getChallengingStatus()) {
            throw new ProjectException(MissionErrorCode.MISSION_ALREADY_COMPLETED);
        }

        userMission.completeMission();

        return MissionConverter.toCompleteResultDTO(userMission);
    }
}
