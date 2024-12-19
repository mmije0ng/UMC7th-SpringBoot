package com.umc.workbook.converter;

import com.umc.workbook.domain.Member;
import com.umc.workbook.domain.enums.Gender;
import com.umc.workbook.dto.member.MemberRequest;
import com.umc.workbook.dto.member.MemberResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;

// 멤버 컨버터
public class MemberConverter {

    // 멤버 등록
    public static Member toMember(MemberRequest.SignUpDTO request){
        Gender gender = switch (request.getGender()) {
            case 1 -> Gender.MALE;
            case 2 -> Gender.FEMALE;
            case 3 -> Gender.NONE;
            default -> null;
        };

        return Member.builder()
                .email(request.getEmail())
                .loginPassword(request.getPassword())
                .nickName(request.getName())
                .gender(gender)
                .birth(formatBirthDate(request))
                .memberAddress(formatFullAddress(request))
                .memberPretendFoodList(new ArrayList<>())
                .role(request.getRole())
                .build();
    }

    // 회원가입 응답 dto 컨버터
    public static MemberResponse.SignUpResultDTO toSignUpResultDTO(Member member){
        return MemberResponse.SignUpResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    // 생년월일 포맷팅 메서드
    private static String formatBirthDate(MemberRequest.SignUpDTO request) {
        return String.format("%s-%s-%s", request.getBirthYear(), request.getBirthMonth(), request.getBirthDay());
    }

    // 전체 주소 포맷팅 메서드
    private static String formatFullAddress(MemberRequest.SignUpDTO request) {
        return String.format("%s %s", request.getAddress(), request.getSpecAddress());
    }
}
