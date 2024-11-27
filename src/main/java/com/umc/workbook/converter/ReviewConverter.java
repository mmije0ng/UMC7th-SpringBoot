package com.umc.workbook.converter;

import com.umc.workbook.domain.Review;
import com.umc.workbook.dto.review.ReviewRequest;
import com.umc.workbook.dto.review.ReviewResponse;

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
}
