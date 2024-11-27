package com.umc.workbook.repository.MemberRepository;

import com.umc.workbook.dto.MemberResponse;

import java.util.Optional;

public interface MemberRepositoryCustom {
    // memberId로 마이페이지 조회
    Optional<MemberResponse.MyPageDTO> findMemberByMemberIdForMyPage(Long memberId);
}
