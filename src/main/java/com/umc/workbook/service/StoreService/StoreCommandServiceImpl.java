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

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreCommandServiceImpl implements StoreCommandService {
    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    // 가게 등록
    @Override
    @Transactional
    public StoreResponse.CreateResultDTO addStore(Long regionId, StoreRequest.CreateDTO request) {
        Region region = regionRepository.findById(regionId).get(); // 지역 조회

        Store store = StoreConverter.toStore(request); // Store 엔티티 생성
        store.setRegion(region);
        storeRepository.save(store); // store 엔티티 저장

        return StoreConverter.toCreateResultDTO(store);
    }
}
