package com.umc.workbook.service.MemberMissionService;

public interface MemberMissionQueryService {
    // 미션 아이디와 멤버 아이디를 통해 존재하는 멤버-미션인지 확인
    Boolean findMemberMissionByMissionIdAndMemberId(Long missionId, Long memberId);
}
