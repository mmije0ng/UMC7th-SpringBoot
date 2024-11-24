package com.umc.workbook.controller;

import com.umc.workbook.apiPayload.ApiResponse;
import com.umc.workbook.dto.StoreResponse;
import com.umc.workbook.service.RegionService.RegionCommandService;
import com.umc.workbook.validation.annotation.ExistCategories;
import com.umc.workbook.validation.annotation.ExistRegion;
import com.umc.workbook.validation.annotation.ExistStore;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated // 클래스 레벨에 추가
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/region")
public class RegionController {

    private final RegionCommandService regionCommandService;


    // 특정 지역에 가게 추가하기
    @PatchMapping()
    ApiResponse<StoreResponse.RegionResultDTO> patchStoreAtRegion (@RequestParam(name = "regionId") @ExistRegion Long regionId,
                                                 @RequestParam(name="storeId") @ExistStore Long storeId) {

        StoreResponse.RegionResultDTO result = regionCommandService.addStoreAtReview(regionId, storeId);
        return ApiResponse.onSuccess(result);
    }
}
