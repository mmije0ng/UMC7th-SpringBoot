package com.umc.workbook.service.ReviewService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.umc.workbook.domain.Member;
import com.umc.workbook.domain.Store;
import com.umc.workbook.dto.ReviewRequest;
import com.umc.workbook.repository.MemberRepository.MemberRepository;
import com.umc.workbook.repository.ReviewRepository.ReviewRepository;
import com.umc.workbook.repository.StoreRepository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReviewQueryServiceImpl implements ReviewQueryService{

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;


    // 리뷰 등록
    @Override
    @Transactional
    public void registerReview(Long memberId, Long storeId, ReviewRequest.InsertDTO request) {
        log.info("리뷰 등록 완료");
    }
}
