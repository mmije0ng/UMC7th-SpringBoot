package com.umc.workbook.dto;

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
    public static class RegionResultDTO{ // 지역에 가게 추가
        Long storeId; // 가게 아이디
        String storeName; // 가게 이름
    }
}
