package com.umc.workbook.service.ReviewService;

import com.umc.workbook.domain.Review;
import com.umc.workbook.domain.Store;
import com.umc.workbook.repository.ReviewRepository.ReviewRepository;
import com.umc.workbook.repository.StoreRepository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReviewQueryServiceImpl implements ReviewQueryService{

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    // 가게 아이디로 페이지 번호와 일치하는 리뷰 목록 조회 (페이지네이션)
    @Override
    public Page<Review> getStoreReviewPage(Long storeId, Integer page) {
        // 가게 조회
        Store store = storeRepository.findById(storeId).get();

        // 리뷰 목록 조회 (페이지네이션)
        Page<Review> storeReviewPage = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));

        return storeReviewPage;
    }
}
