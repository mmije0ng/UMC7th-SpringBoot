package com.umc.workbook.service.MemberService;

import com.umc.workbook.domain.Member;
import com.umc.workbook.dto.MemberRequest;

public interface MemberCommandService {

    // 회원가입
    Member joinMember(MemberRequest.JoinDTO request);
}
