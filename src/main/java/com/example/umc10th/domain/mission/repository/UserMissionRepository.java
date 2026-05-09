package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.UserMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    @Query("""
        select um
        from UserMission um
        where um.member.id = :memberId
          and um.challengingStatus = :challengingStatus
        order by um.createdAt desc
    """)
    Page<UserMission> findMyMissionsByStatus(
            @Param("memberId") Long memberId,
            @Param("challengingStatus") Boolean challengingStatus,
            Pageable pageable
    );

    @Query("""
        select um
        from UserMission um
        where um.member.id = :memberId
          and um.mission.id = :missionId
    """)
    Optional<UserMission> findByMemberIdAndMissionId(
            @Param("memberId") Long memberId,
            @Param("missionId") Long missionId
    );

    boolean existsByMember_IdAndMission_Id(Long memberId, Long missionId);
    boolean existsByMember_IdAndMission_IdAndChallengingStatus(Long memberId, Long missionId, Boolean challengingStatus);


    long countByMember_IdAndChallengingStatus(Long memberId, Boolean challengingStatus);
}
