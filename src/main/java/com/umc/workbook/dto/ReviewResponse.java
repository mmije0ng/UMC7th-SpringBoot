package com.umc.workbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ReviewResponse {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResultDTO { // 리뷰 등록
        Long reviewId; // 리뷰 아이디
        LocalDateTime createdAt; // 생성일자
    }
}
