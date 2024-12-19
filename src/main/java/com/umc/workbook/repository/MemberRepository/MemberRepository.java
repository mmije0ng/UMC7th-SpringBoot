package com.umc.workbook.repository.MemberRepository;

import com.umc.workbook.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom  {
    // 이메일로 멤버 찾기
    Optional<Member> findByEmail(String email);
}
