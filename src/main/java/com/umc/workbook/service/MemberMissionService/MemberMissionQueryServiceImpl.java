package com.umc.workbook.service.MemberMissionService;

import com.umc.workbook.repository.MemberMissionRepository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService {
    private final MemberMissionRepository memberMissionRepository;

    // 미션 아이디와 멤버 아이디를 통해 존재하는 멤버-미션인지 확인
    @Override
    public Boolean findMemberMissionByMissionIdAndMemberId(Long missionId, Long memberId) {
        return memberMissionRepository
                .findMemberMissionByMissionIdAndMemberId(missionId, memberId)
                .isPresent();
    }
}
