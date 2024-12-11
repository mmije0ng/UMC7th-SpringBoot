package com.umc.workbook.service.ReviewService;

import com.umc.workbook.domain.Review;
import org.springframework.data.domain.Page;

public interface ReviewQueryService {

    // 가게 아이디로 페이지 번호와 일치하는 리뷰 목록 조회 (페이지네이션)
    Page<Review> getStoreReviewPage(Long storeId, Integer page);
}
