package com.umc.workbook.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class StoreResponse {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResultDTO { // 지역에 가게 추가
        Long storeId; // 가게 아이디
        LocalDateTime createdAt; // 생성일자
    }
}
