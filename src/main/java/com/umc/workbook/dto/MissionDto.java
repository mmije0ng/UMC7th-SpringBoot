package com.umc.workbook.dto;

import lombok.*;

public class MissionDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MissionStatusResponse {
        protected String missionContent; // 미션 내용
        protected Integer missionMoney; // 미션 기준 금액
        protected Integer missionPoint; // 미션 적립 포인트
        protected String missionStatus; // 미션 진행 여부 (진행중, 진행완료)
        protected String storeName; // 가게 이름
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionDdayResponse extends MissionStatusResponse {
        private String dDay; // 디데이
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class HomeResponse {
        private Integer point; // 멤버 포인트
        private MissionDdayResponse missionResponse;
    }
}