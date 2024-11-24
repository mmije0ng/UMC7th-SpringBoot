package com.umc.workbook.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

public class ReviewRequest {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class InsertDTO {
        @NotNull
        private Double reviewScore; // 리뷰 평점
        @NotNull
        private String reviewContent; // 리뷰 내용
        private List<String> reviewImageList; // 리뷰 이미지
    }
}
