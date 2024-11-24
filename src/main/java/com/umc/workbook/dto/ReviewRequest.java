package com.umc.workbook.dto;

import lombok.*;

import java.util.List;

public class ReviewRequest {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class InsertDTO {
        private Double reviewScore; // 리뷰 평점
        private String reviewContent; // 리뷰 내용
        private List<String> reviewImageList; // 리뷰 이미지
    }
}
