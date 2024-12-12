package com.umc.workbook.service.ReviewService;

import com.umc.workbook.domain.Member;
import com.umc.workbook.domain.Review;
import com.umc.workbook.domain.Store;
import com.umc.workbook.repository.MemberRepository.MemberRepository;
import com.umc.workbook.repository.ReviewRepository.ReviewRepository;
import com.umc.workbook.repository.StoreRepository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReviewQueryServiceImpl implements ReviewQueryService{

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    private static final Integer PAGE_SIZE=10;

    // 페이지 정렬, 10개씩
    private Pageable pageRequest(Integer pageNumber) {
        return PageRequest.of(pageNumber, PAGE_SIZE);
    }

    // 가게 아이디로 페이지 번호와 일치하는 리뷰 목록 조회 (페이지네이션)
    @Override
    public Page<Review> getStoreReviewPage(Long storeId, Integer page) {
        // 가게 조회
        Store store = storeRepository.findById(storeId).get();

        // 리뷰 목록 조회 (페이지네이션)
        Page<Review> storeReviewPage = reviewRepository.findAllByStore(store, pageRequest(page));

        return storeReviewPage;
    }

    // 멤버 아이디로 페이지 번호와 일치하는 리뷰 목록 조회 (페이지네이션)
    @Override
    public Page<Review> getMemberReviewPage(Long memberId, Integer page) {
        // 멤버 조회
        Member member = memberRepository.findById(memberId).get();

        // 리뷰 목록 조회 (페이지네이션)
        Page<Review> memberReviewPage = reviewRepository.findAllByMember(member, pageRequest(page));

        return memberReviewPage;
    }
}
