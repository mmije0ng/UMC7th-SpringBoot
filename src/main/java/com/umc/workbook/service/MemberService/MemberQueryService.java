package com.umc.workbook.service.MemberService;

import com.umc.workbook.dto.member.MemberResponse;

public interface MemberQueryService {
    // 마이페이지 조회
    MemberResponse.MyPageDTO findMemberByForMyPage(Long memberId);

    // 아이디를 통해 존재하는 멤버인지 확인
    Boolean existsMemberById(Long memberId);
}
