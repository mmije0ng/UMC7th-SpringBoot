package com.umc.workbook.converter;

import com.umc.workbook.domain.Member;
import com.umc.workbook.domain.enums.Gender;
import com.umc.workbook.dto.MemberRequest;
import com.umc.workbook.dto.MemberResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;

// 멤버 컨버터
public class MemberConverter {

    // 멤버 등록
    public static Member toMember(MemberRequest.JoinDTO request){
        Gender gender = null;
        switch (request.getGender()){
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }

        return Member.builder()
                .loginId(request.getLoginId())
                .loginPassword(request.getLoginPassword())
                .nickName(request.getNickName())
                .gender(gender)
                .birth(request.getBirth())
                .memberAddress(request.getMemberAddress())
                .phoneNumber(request.getPhoneNumber())
                .memberPretendFoodList(new ArrayList<>()) // new ArrayList<>()로 초기화
                .build();
    }


    // 회원가입 응답 dto 컨버터
    public static MemberResponse.JoinResultDTO toJointResultDTO(Member member){
        return MemberResponse.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

}
