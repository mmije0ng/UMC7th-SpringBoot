package com.umc.workbook.service.MemberService;

import com.umc.workbook.dto.MemberResponse;

public interface MemberQueryService {
    // 마이페이지 조회
    MemberResponse.MyPageDTO findMemberByForMyPage(Long memberId);
}
