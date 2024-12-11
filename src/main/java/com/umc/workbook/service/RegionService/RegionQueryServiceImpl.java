package com.umc.workbook.service.RegionService;

import com.umc.workbook.repository.RegionRepository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegionQueryServiceImpl implements RegionQueryService{
    private final RegionRepository regionRepository;

    // 아이디를 통해 존재하는 지역인지 확인
    @Override
    public Boolean existsRegionById(Long regionId) {
        return regionRepository.existsById(regionId);
    }
}
