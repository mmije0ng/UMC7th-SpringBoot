package com.umc.workbook.service.ReviewService;

import com.umc.workbook.domain.Member;
import com.umc.workbook.domain.Store;
import com.umc.workbook.dto.ReviewDto;
import com.umc.workbook.repository.MemberRepository.MemberRepository;
import com.umc.workbook.repository.ReviewRepository.ReviewRepository;
import com.umc.workbook.repository.StoreRepository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService{
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    // 리뷰 등록
    @Override
    public void registerReview(Long memberId, Long storeId, ReviewDto.InsertRequest requestDto) {
        // memberId에 해당하는 Member 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member가 존재하지 않습니다."));

        // reviewId에 해당하는 Store 조회
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("Store가 존재하지 않습니다."));

        reviewRepository.insertReview(member, store, requestDto);

        log.info("리뷰 등록 완료");
    }
}
