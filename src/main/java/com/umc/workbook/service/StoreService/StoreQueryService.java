package com.umc.workbook.service.StoreService;

import com.umc.workbook.domain.Store;

import java.util.List;
import java.util.Optional;

public interface StoreQueryService {
    List<Store> findStoresByNameAndScore(String name, Float score);
}
