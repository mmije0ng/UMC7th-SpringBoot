package com.umc.workbook.dto.mission;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

public class MissionRequest {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CreateMissionDTO { // 가게에 미션 추가 요청 dto
        @NotBlank
        private String content; // 미션 내용

        @Min(value = 1000)
        private Integer money; // 기준 금액

        @Positive
        private Integer point; // 적립 포인트
    }
}
