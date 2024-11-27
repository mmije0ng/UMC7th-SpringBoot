package com.umc.workbook.service.StoreService;

import com.umc.workbook.domain.Store;
import com.umc.workbook.dto.store.StoreRequest;
import com.umc.workbook.dto.store.StoreResponse;

import java.util.List;

public interface StoreQueryService {

    // 가게이름, 평점으로 조회
    List<Store> findStoresByNameAndScore(String name, Float score);
}
