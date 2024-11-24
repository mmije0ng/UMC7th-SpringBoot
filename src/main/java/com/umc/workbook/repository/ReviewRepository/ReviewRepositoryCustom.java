package com.umc.workbook.repository.ReviewRepository;

import com.umc.workbook.domain.Member;
import com.umc.workbook.domain.Store;
import com.umc.workbook.dto.ReviewRequest;

public interface ReviewRepositoryCustom {
    // 리뷰 등록
    void insertReview(Member member, Store store, ReviewRequest.InsertDTO requestDto);
}
