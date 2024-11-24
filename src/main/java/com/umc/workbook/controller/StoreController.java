package com.umc.workbook.controller;

import com.umc.workbook.apiPayload.ApiResponse;
import com.umc.workbook.dto.StoreRequest;
import com.umc.workbook.dto.StoreResponse;
import com.umc.workbook.service.StoreService.StoreQueryService;
import com.umc.workbook.validation.annotation.ExistRegion;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated // 클래스 레벨에 추가
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/store")
public class StoreController {

    private final StoreQueryService storeQueryService;

    // 특정 지역에 가게 추가하기
    @PostMapping
    ApiResponse<StoreResponse.CreateResultDTO> createStore (@RequestParam(name = "regionId") @ExistRegion Long regionId,
                                            @RequestBody @Valid StoreRequest.CreateDTO request) {
        StoreResponse.CreateResultDTO result = storeQueryService.addStore(regionId, request);
        return ApiResponse.onSuccess(result);
    }
}
