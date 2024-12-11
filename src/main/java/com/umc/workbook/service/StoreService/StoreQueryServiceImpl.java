package com.umc.workbook.service.StoreService;

import com.umc.workbook.converter.StoreConverter;
import com.umc.workbook.domain.Region;
import com.umc.workbook.domain.Store;
import com.umc.workbook.dto.store.StoreRequest;
import com.umc.workbook.dto.store.StoreResponse;
import com.umc.workbook.repository.RegionRepository.RegionRepository;
import com.umc.workbook.repository.StoreRepository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService{

    private final StoreRepository storeRepository;

    // 가게이름, 평점으로 조회
    @Override
    public List<Store> findStoresByNameAndScore(String name, Float score) {
        List<Store> filteredStores = storeRepository.dynamicQueryWithBooleanBuilder(name, score);

        filteredStores.forEach(store -> System.out.println("Store: " + store));

        return filteredStores;
    }

    // 아이디를 통해 존재하는 가게인지 확인
    @Override
    public Boolean existsStoreById(Long storeId) {
        return storeRepository.existsById(storeId);
    }
}