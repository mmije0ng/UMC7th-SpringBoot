package com.umc.workbook.service.RegionService;

import com.umc.workbook.dto.StoreResponse;

public interface RegionCommandService {

    // 특정 지역에 가게 추가하기
    StoreResponse.RegionResultDTO addStoreAtReview(Long regionId, Long storeId);
}
