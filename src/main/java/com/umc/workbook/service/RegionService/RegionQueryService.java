package com.umc.workbook.service.RegionService;

public interface RegionQueryService {
    // 아이디를 통해 존재하는 지역인지 확인
    Boolean existsRegionById(Long regionId);
}
