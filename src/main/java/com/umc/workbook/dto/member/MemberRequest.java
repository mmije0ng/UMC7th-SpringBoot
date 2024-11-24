package com.umc.workbook.dto.member;

import com.umc.workbook.domain.enums.Gender;
import com.umc.workbook.validation.annotation.ExistCategories;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;

import java.util.List;

public class MemberRequest {

    @Getter
    // 회원가입 요청 dto
    public static class JoinDTO{
        private String loginId; // 로그인 아이디
        private String loginPassword; // 비밀번호
        private String nickName; // 닉네임
        private Integer gender; // 성별, 0이면 남자, 1이면 여자
        private String birth; // 생년월일
        private String memberAddress; // 주소
        private String phoneNumber; // 전화번호
        @ExistCategories // 커스텀 어노테이션
        private List<Long> memberPretendFoodList; // 음식 선호 카테고리
    }
}
