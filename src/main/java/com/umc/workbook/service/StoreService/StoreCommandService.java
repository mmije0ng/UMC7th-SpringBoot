package com.umc.workbook.service.StoreService;

import com.umc.workbook.dto.store.StoreRequest;
import com.umc.workbook.dto.store.StoreResponse;

public interface StoreCommandService {
    // 가게 등록
    StoreResponse.CreateResultDTO addStore(Long regionId, StoreRequest.CreateDTO request);
}
