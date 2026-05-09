package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MissionReqDTO {

    @Getter
    @NoArgsConstructor
    public static class MyProgressMissionRequestDTO {

        @NotNull(message = "memberId는 필수입니다.")
        private Long memberId;

        @NotNull(message = "page는 필수입니다.")
        @Min(value = 0, message = "page는 0 이상이어야 합니다.")
        private Integer page;

        @NotNull(message = "size는 필수입니다.")
        @Min(value = 1, message = "size는 1 이상이어야 합니다.")
        private Integer size;
    }
}
