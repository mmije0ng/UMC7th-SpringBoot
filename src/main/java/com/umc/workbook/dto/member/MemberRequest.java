package com.umc.workbook.dto.member;

import com.umc.workbook.domain.enums.Role;
import com.umc.workbook.validation.annotation.ExistCategories;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class MemberRequest {

    @Getter
    @Setter   // thymeleaf에서 사용하기 위해 추가
    // 회원가입 요청 dto
    public static class SignUpDTO {
        @NotBlank
        String name; // 닉네임

        @Email
        String email; // 이메일

        @NotBlank
        String password; // 비밀번호

        @NotNull
        private Integer gender; // 성별, 0이면 남자, 1이면 여자

        @NotNull
        Integer birthYear;

        @NotNull
        Integer birthMonth;

        @NotNull
        Integer birthDay;

        @Size(min = 5, max = 12)
        String address;

        @Size(min = 5, max = 12)
        String specAddress;

        @ExistCategories // 커스텀 어노테이션
        private List<Long> preferCategory; // 음식 선호 카테고리

        @NotNull
        Role role; // 역할
    }
}
