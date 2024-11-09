package com.umc.workbook.repository.StoreRepository;

import com.umc.workbook.domain.Store;

import java.util.List;
import java.util.Optional;


public interface StoreRepositoryCustom {
    List<Store> dynamicQueryWithBooleanBuilder(String name, Float score);
}