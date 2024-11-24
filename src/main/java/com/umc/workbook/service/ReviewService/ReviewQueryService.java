package com.umc.workbook.service.ReviewService;

import com.umc.workbook.dto.ReviewRequest;

public interface ReviewQueryService {
    // 리뷰 등록
    void registerReview(Long memberId, Long storeId, ReviewRequest.InsertDTO requestDto);
}
