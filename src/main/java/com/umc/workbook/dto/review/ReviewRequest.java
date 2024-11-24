package com.umc.workbook.dto.review;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

public class ReviewRequest {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CreateReviewDTO {
        @DecimalMin(value = "0.5")
        private Double reviewScore; // 리뷰 평점

        @NotBlank
        private String reviewContent; // 리뷰 내용

        private List<String> reviewImageList; // 리뷰 이미지
    }
}
