package com.umc.workbook.repository.MemberRepository;

import com.umc.workbook.dto.MemberDto;

import java.util.Optional;

public interface MemberRepositoryCustom {
    // memberId로 마이페이지 조회
    Optional<MemberDto.MyPage> findMemberByMemberIdForMyPage(Long memberId);
}
