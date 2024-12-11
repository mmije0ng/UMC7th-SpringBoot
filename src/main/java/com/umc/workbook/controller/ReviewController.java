package com.umc.workbook.controller;

import com.umc.workbook.apiPayload.ApiResponse;
import com.umc.workbook.dto.review.ReviewRequest;
import com.umc.workbook.dto.review.ReviewResponse;
import com.umc.workbook.dto.store.StoreResponse;
import com.umc.workbook.service.ReviewService.ReviewCommandService;
import com.umc.workbook.service.StoreService.StoreQueryService;
import com.umc.workbook.validation.annotation.ExistMember;
import com.umc.workbook.validation.annotation.ExistStore;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated // 클래스 레벨에 추가
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewCommandService reviewCommandService;

    // 리뷰 추가
    @PostMapping
    ApiResponse<ReviewResponse.CreateReviewResultDTO> createReview (@RequestParam(name = "memberId") @ExistMember Long memberId,
                                                                    @RequestParam(name="storeId") @ExistStore Long storeId,
                                                                    @RequestBody @Valid ReviewRequest.CreateReviewDTO request) {
        ReviewResponse.CreateReviewResultDTO result = reviewCommandService.addReview(memberId, storeId, request);
        return ApiResponse.onSuccess(result);
    }
}
