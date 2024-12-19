package com.umc.workbook.dto.member;

import lombok.*;

import java.time.LocalDateTime;

public class MemberResponse {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    // 마이페이지 조회 응답 dto
    public static class MyPageDTO {
        private String nickName; // 닉네임
        private String email; // 이메일
        private Boolean isPhoneVerified; // 전화번호 인증 여부
        private String phoneNumber; // 전화번호
        private Integer point; // 포인트
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignUpResultDTO {
        Long memberId; // 멤버 pk
        LocalDateTime createdAt; // 생성일자
    }
}
