package com.umc.workbook.repository.ReviewRepository;

import com.umc.workbook.domain.Member;
import com.umc.workbook.domain.Review;
import com.umc.workbook.domain.Store;
import com.umc.workbook.dto.review.ReviewRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ReviewRepositoryCustom {
    // 리뷰 등록
    void insertReview(Member member, Store store, ReviewRequest.CreateReviewDTO requestDto);
}
