package com.umc.workbook.repository.MemberRepository;

import com.umc.workbook.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
}
