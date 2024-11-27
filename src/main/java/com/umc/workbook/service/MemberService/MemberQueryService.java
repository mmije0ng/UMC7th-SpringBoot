package com.umc.workbook.service.MemberService;

import com.umc.workbook.dto.member.MemberResponse;

public interface MemberQueryService {
    // 마이페이지 조회
    MemberResponse.MyPageDTO findMemberByForMyPage(Long memberId);
}
