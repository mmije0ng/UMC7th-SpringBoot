package com.umc.workbook.converter;

import com.umc.workbook.domain.Store;
import com.umc.workbook.dto.StoreRequest;
import com.umc.workbook.dto.StoreResponse;

public class StoreConverter {

    // 가게 엔티티 생성
    public static Store toStore(StoreRequest.CreateDTO request){
        return  Store.builder()
                .storeName(request.getStoreName())
                .storeImage(JsonConverter.convertStringToJson(request.getStoreImage()))
                .storeAddress(request.getStoreAddress())
                .build();
    }

    // 등록 성공 dto로 변환
    public static StoreResponse.CreateResultDTO toCreateResultDTO(Store store){
        return StoreResponse.CreateResultDTO.builder()
                .storeId(store.getId())
                .createdAt(store.getCreatedAt())
                .build();
    }
}
