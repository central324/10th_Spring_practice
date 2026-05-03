package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mission")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "reward", nullable = false)
    private Integer reward;

    @Column(name = "deadline", nullable = false)
    private LocalDateTime deadline;

    @Column(name = "mission_spec", nullable = false, length = 255)
    private String missionSpec;

    @OneToMany(mappedBy = "mission")
    @Builder.Default
    private List<UserMission> userMissionList = new ArrayList<>();
}
