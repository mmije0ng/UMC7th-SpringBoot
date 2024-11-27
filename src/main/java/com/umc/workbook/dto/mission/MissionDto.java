package com.umc.workbook.dto.mission;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Page;

public class MissionDto {

    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StatusResponse {
        protected String missionContent; // 미션 내용
        protected Integer missionMoney; // 미션 기준 금액
        protected Integer missionPoint; // 미션 적립 포인트
        protected String missionStatus; // 미션 진행 여부 (진행중, 진행완료)
        protected String storeName; // 가게 이름
    }

    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DdayResponse extends StatusResponse {
        private String dDay; // 디데이
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PagedHomeResponse {
        private Integer point; // 멤버 포인트
        private Page<DdayResponse> missions; // 페이지네이션이 적용된 미션목록
    }
}