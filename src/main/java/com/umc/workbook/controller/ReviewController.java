package com.umc.workbook.controller;

import com.umc.workbook.apiPayload.ApiResponse;
import com.umc.workbook.dto.ReviewRequest;
import com.umc.workbook.dto.ReviewResponse;
import com.umc.workbook.service.ReviewService.ReviewQueryService;
import com.umc.workbook.validation.annotation.ExistMember;
import com.umc.workbook.validation.annotation.ExistStore;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated // 클래스 레벨에 추가
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    // 리뷰 추가
    @PostMapping
    ApiResponse<ReviewResponse.CreateResultDTO> createReview (@RequestParam(name = "memberId") @ExistMember Long memberId,
                                                             @RequestParam(name="storeId") @ExistStore Long storeId,
                                                             @RequestBody @Valid ReviewRequest.InsertDTO request) {
        ReviewResponse.CreateResultDTO result = reviewQueryService.addReview(memberId, storeId, request);
        return ApiResponse.onSuccess(result);
    }
}
