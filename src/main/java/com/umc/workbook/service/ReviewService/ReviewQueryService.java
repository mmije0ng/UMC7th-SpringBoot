package com.umc.workbook.service.ReviewService;

import com.umc.workbook.dto.ReviewRequest;
import com.umc.workbook.dto.ReviewResponse;

public interface ReviewQueryService {
    // 리뷰 등록
    ReviewResponse.CreateResultDTO addReview(Long memberId, Long storeId, ReviewRequest.InsertDTO requestDto);
}
