package com.umc.workbook.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponse {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReviewResultDTO { // 리뷰 등록
        Long reviewId; // 리뷰 아이디
        LocalDateTime createdAt; // 생성일자
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewListDTO{ // 리뷰 목록 응답
        List<ReviewPreViewDTO> reviewList; // 리뷰 목록
        Integer listSize; // 현재 페이지의 리뷰 목록 개수
        Integer totalPage; // 총 페이지
        Long totalElements; // 총 리뷰 개수
        Boolean isFirst; // 페이지 처음 여부
        Boolean isLast; // 페이지 마지막 여부
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewDTO{ // 리뷰 응답
        String ownerNickName; // 리뷰 작성자 이름
        Float score; // 리뷰 평점
        String content; // 리뷰 내용
        LocalDate createdAt; // 작성 일자
    }
}
