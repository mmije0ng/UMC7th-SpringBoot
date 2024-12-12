package com.umc.workbook.controller;

import com.umc.workbook.apiPayload.ApiResponse;
import com.umc.workbook.converter.ReviewConverter;
import com.umc.workbook.domain.Review;
import com.umc.workbook.dto.review.ReviewRequest;
import com.umc.workbook.dto.review.ReviewResponse;
import com.umc.workbook.service.ReviewService.ReviewCommandService;
import com.umc.workbook.service.ReviewService.ReviewQueryService;
import com.umc.workbook.validation.annotation.CheckPage;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated // 클래스 레벨에 추가
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    // 리뷰 추가
    @PostMapping
    ApiResponse<ReviewResponse.CreateReviewResultDTO> createReview (@RequestParam(name = "memberId") @ExistMember Long memberId,
                                                                    @RequestParam(name="storeId") @ExistStore Long storeId,
                                                                    @RequestBody @Valid ReviewRequest.CreateReviewDTO request) {
        ReviewResponse.CreateReviewResultDTO result = reviewCommandService.addReview(memberId, storeId, request);
        return ApiResponse.onSuccess(result);
    }

    // 특정 가게 리뷰 목록 조회
    @GetMapping("/store/{storeId}")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API", description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다."),
            @Parameter(name = "page", description = "페이지 번호, query parameter 입니다.")
    })
    public ApiResponse<ReviewResponse.ReviewPreViewListDTO> getStoreReviewList(@ExistStore @PathVariable(name="storeId") Long storeId,  @CheckPage Integer page){
        log.info("가게 리뷰 목록 조회 페이지번호: "+page);

        // 가게의 리뷰 페이지
        Page<Review> storeReviewPage = reviewQueryService.getStoreReviewPage(storeId, page);

        return ApiResponse.onSuccess(ReviewConverter.toReviewPreViewListDTO(storeReviewPage));
    }

    // 사용자 리뷰 목록 조회
    @GetMapping("/member/{memberId}")
    @Operation(summary = "사용자의 리뷰 목록 조회 API", description = "사용자의 모든 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디, path variable 입니다."),
            @Parameter(name = "page", description = "페이지 번호, query parameter 입니다.")
    })
    public ApiResponse<ReviewResponse.ReviewPreViewListDTO> getMemberReviewList(@ExistStore @PathVariable(name="memberId") Long memberId, @CheckPage Integer page){
        log.info("멤버 리뷰 목록 조회 페이지번호: "+page);

        // 멤버의 리뷰 페이지
        Page<Review> memberReviewPage = reviewQueryService.getMemberReviewPage(memberId, page);

        return ApiResponse.onSuccess(ReviewConverter.toReviewPreViewListDTO(memberReviewPage));
    }
}
