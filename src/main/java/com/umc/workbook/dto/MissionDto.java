package com.umc.workbook.dto;

import lombok.*;

public class MissionDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MissionStatusResponse{
        private String missionContent; // 미션 내용
        private Integer missionMoney; // 미션 기준 금액
        private Integer missionPoint; // 미션 적립 포인트
        private String missionStatus; // 미션 진행 여부 (진행중, 진행완료)
        private String storeName; // 가게 이름
    }
}
