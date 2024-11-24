package com.umc.workbook.repository.ReviewRepository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.umc.workbook.domain.*;
import com.umc.workbook.dto.review.ReviewRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    // List<String>를 JSON 문자열로 변환하는 메서드
    private String convertImageListToJson(List<String> imageList) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(imageList);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("이미지 리스트 JSON 변환 중 오류 발생", e);
        }
    }

    // 리뷰 등록
    @Override
    @Transactional
    public void insertReview(Member member, Store store, ReviewRequest.CreateReviewDTO requestDto) {
        // 리뷰 이미지 리스트를 JSON 문자열로 변환
        String imageJson = convertImageListToJson(requestDto.getReviewImageList());

        // Review 엔티티 생성 후 EntityManager로 persist
        Review review = Review.builder()
                .member(member)
                .store(store)
                .reviewScore(requestDto.getReviewScore())
                .reviewContent(requestDto.getReviewContent())
                .reviewImage(imageJson) // JSON 문자열로 처리
                .build();

        entityManager.persist(review); // 리뷰 저장
    }
}
