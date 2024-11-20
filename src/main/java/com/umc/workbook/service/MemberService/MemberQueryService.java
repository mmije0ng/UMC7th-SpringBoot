package com.umc.workbook.service.MemberService;

import com.umc.workbook.dto.MemberDto;

public interface MemberQueryService {
    // 마이페이지 조회
    MemberDto.MyPage findMemberByForMyPage(Long memberId);
}
