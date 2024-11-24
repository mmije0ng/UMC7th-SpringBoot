package com.umc.workbook.dto.mission;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

public class MissionRequest {

    public static class CreateDTO{
        @NotNull
        private String content; // 미션 내용

        private Integer money; // 기준 금액

        private Integer point; // 적립 포인트
    }
}
