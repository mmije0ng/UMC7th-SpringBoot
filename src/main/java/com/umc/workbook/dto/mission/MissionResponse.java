package com.umc.workbook.dto.mission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MissionResponse {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResultDTO { // 미션 등록
        Long missionId; // 미션 아이디
        LocalDateTime createdAt; // 생성일자
    }
}
