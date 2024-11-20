package com.umc.workbook.service.ReviewService;

import com.umc.workbook.dto.ReviewDto;

public interface ReviewQueryService {
    // 리뷰 등록
    void registerReview(Long memberId, Long storeId, ReviewDto.InsertRequest requestDto);
}
