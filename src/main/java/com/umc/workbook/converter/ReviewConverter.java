package com.umc.workbook.converter;

import com.umc.workbook.domain.Review;
import com.umc.workbook.dto.review.ReviewRequest;
import com.umc.workbook.dto.review.ReviewResponse;
import com.umc.workbook.dto.store.StoreResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    // 리뷰 엔티티 전환
    public static Review toReview(ReviewRequest.CreateReviewDTO request){
        return Review.builder()
                .reviewScore(request.getReviewScore())
                .reviewContent(request.getReviewContent())
                .reviewImage(JsonConverter.convertImageListToJson(request.getReviewImageList()))
                .build();
    }

    // 리뷰 등록 응답 dto 전환
    public static ReviewResponse.CreateReviewResultDTO toCreateResultDto(Review review){
        return ReviewResponse.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    // 리뷰 조회 관련 컨버터
    public static ReviewResponse.ReviewPreViewDTO toReviewPreViewDTO(Review review){
        return ReviewResponse.ReviewPreViewDTO.builder()
                .ownerNickName(review.getMember().getNickName())
                .score(review.getReviewScore().floatValue())
                .content(review.getReviewContent())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

    // 리뷰 목록 조회 관련 컨버터
    // Page를 파라미터로 받음
    public static ReviewResponse.ReviewPreViewListDTO toReviewPreViewListDTO(Page<Review> storeReviewPages){
        // Review 리스트를 ReviewPreViewDTO 리스트로 변환
        List<ReviewResponse.ReviewPreViewDTO> reviewList = storeReviewPages.stream()
                .map(ReviewConverter::toReviewPreViewDTO) // Review를 ReviewPreViewDTO로 변환
                .toList();

        return ReviewResponse.ReviewPreViewListDTO.builder()
                .isFirst(storeReviewPages.isFirst())
                .isLast(storeReviewPages.isLast())
                .totalPage(storeReviewPages.getTotalPages())
                .totalElements(storeReviewPages.getTotalElements())
                .listSize(reviewList.size())
                .reviewList(reviewList)
                .build();
    }
}
