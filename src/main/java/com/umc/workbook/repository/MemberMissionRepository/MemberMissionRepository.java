package com.umc.workbook.repository.MemberMissionRepository;

import com.umc.workbook.domain.Member;
import com.umc.workbook.domain.Mission;
import com.umc.workbook.domain.enums.MissionStatus;
import com.umc.workbook.domain.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    Optional<MemberMission> findMemberMissionByMissionIdAndMemberId(Long missionId, Long memberId);
    
    // 멤버, 미션 상태와 일치하는 멤버-미션을 페이지로 반환
    Page<MemberMission> findAllMemberMissionsByMemberAndStatus(Member member, MissionStatus status, Pageable pageable);
}
