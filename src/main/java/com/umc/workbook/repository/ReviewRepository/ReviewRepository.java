package com.umc.workbook.repository.ReviewRepository;

import com.umc.workbook.domain.Review;
import com.umc.workbook.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {

    // 가게와 페이지 번호로 리뷰 목록 조회 (페이지네이션)
    Page<Review> findAllByStore(Store store, PageRequest pageRequest);
}
