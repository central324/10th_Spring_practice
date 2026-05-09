package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("""
        select m
        from Mission m
        join m.store s
        join s.region r
        where r.id = :regionId
        order by m.deadline asc
    """)
    Page<Mission> findAvailableMissionsByRegionId(
            @Param("regionId") Long regionId,
            Pageable pageable
    );

    @Query("""
        select m
        from Mission m
        join m.store s
        join s.region r
        where r.id = :regionId
        order by m.deadline asc
    """)
    List<Mission> findTopMissionsByRegionId(
            @Param("regionId") Long regionId,
            Pageable pageable
    );
}
