package com.umc.workbook.service.StoreService;

import com.umc.workbook.domain.Store;
import com.umc.workbook.dto.StoreRequest;
import com.umc.workbook.dto.StoreResponse;

import java.util.List;
import java.util.Optional;

public interface StoreQueryService {

    // 가게 등록
    StoreResponse.CreateResultDTO addStore(Long regionId, StoreRequest.CreateDTO request);

    // 가게이름, 평점으로 조회
    List<Store> findStoresByNameAndScore(String name, Float score);
}
