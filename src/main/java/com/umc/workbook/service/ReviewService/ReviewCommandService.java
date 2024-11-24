package com.umc.workbook.service.ReviewService;

import com.umc.workbook.dto.review.ReviewRequest;
import com.umc.workbook.dto.review.ReviewResponse;

public interface ReviewCommandService {

    // 리뷰 등록
    ReviewResponse.CreateResultDTO addReview(Long memberId, Long storeId, ReviewRequest.CreateDTO requestDto);
}
