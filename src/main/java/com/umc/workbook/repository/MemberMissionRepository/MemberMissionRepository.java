package com.umc.workbook.repository.MemberMissionRepository;

import com.umc.workbook.domain.mapping.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    Optional<MemberMission> findMemberMissionByMissionIdAndMemberId(Long missionId, Long memberId);
}
