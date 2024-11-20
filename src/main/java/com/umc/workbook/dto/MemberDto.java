package com.umc.workbook.dto;

import lombok.*;

public class MemberDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MyPage{
        private String nickName; // 닉네임
        private String email; // 이메일
        private Boolean isPhoneVerified; // 전화번호 인증 여부
        private String phoneNumber; // 전화번호
        private Integer point; // 포인트
    }
}
