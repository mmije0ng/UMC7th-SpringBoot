package com.umc.workbook.dto.store;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class StoreRequest {

    @Getter
    // 지역에 가게 추가
    public static class CreateDTO {
        @NotNull
        private String storeName; // 가게 이름
        @NotNull
        private String storeAddress; // 가게 주소
        private String storeImage; // 가게 이미지 URL
    }
}
