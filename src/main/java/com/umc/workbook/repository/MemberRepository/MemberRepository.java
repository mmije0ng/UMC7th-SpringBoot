package com.umc.workbook.repository.MemberRepository;

import com.umc.workbook.domain.Member;
import com.umc.workbook.domain.Mission;
import com.umc.workbook.repository.MissionRepository.MissionRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom  {
}
