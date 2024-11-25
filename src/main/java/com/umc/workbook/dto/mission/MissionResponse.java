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
    public static class CreateMissionResultDTO { // 미션 등록
        Long missionId; // 미션 아이디
        LocalDateTime createdAt; // 생성일자
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateMemberMissionResultDTO { // 도전 중인 미션으로 변경
        String missionContent; // 미션 이름
        String missionStatus; // 미션 상태
        LocalDateTime expiredAt; // 만료 일자
    }

}
