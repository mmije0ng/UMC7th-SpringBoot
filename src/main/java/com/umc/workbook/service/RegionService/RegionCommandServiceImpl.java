package com.umc.workbook.service.RegionService;

import com.umc.workbook.converter.StoreConverter;
import com.umc.workbook.domain.Region;
import com.umc.workbook.domain.Store;
import com.umc.workbook.dto.StoreResponse;
import com.umc.workbook.repository.RegionRepository.RegionRepository;
import com.umc.workbook.repository.StoreRepository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegionCommandServiceImpl implements RegionCommandService{

    private final RegionRepository regionRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    //특정 지역에 가게 추가하기
    public StoreResponse.RegionResultDTO addStoreAtReview(Long regionId, Long storeId) {
        Region region = regionRepository.findById(regionId).get();
        Store store = storeRepository.findById(storeId).get();

        store.setRegion(region);
        
        return StoreConverter.regionResultDTO(store);

    }
}
