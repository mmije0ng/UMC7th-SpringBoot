package com.umc.workbook.converter;

import com.umc.workbook.domain.Store;
import com.umc.workbook.dto.StoreResponse;

public class StoreConverter {

    // 지역에 가게 추가할 때 응답 dto로 전환
    public static StoreResponse.RegionResultDTO regionResultDTO(Store store){
        return StoreResponse.RegionResultDTO.builder()
                .storeId(store.getId())
                .storeName(store.getStoreName())
                .build();
    }
}
