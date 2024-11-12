package com.umc.workbook.service.MemberService;

import com.umc.workbook.dto.MemberDto;
import com.umc.workbook.repository.MemberRepository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberRepository memberRepository;

    // 마이페이지 조회
    @Override
    public MemberDto.MyPage findMemberByForMyPage(Long memberId) {
        return memberRepository.findMemberByMemberIdForMyPage(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member가 존재하지 않습니다."));
    }
}
