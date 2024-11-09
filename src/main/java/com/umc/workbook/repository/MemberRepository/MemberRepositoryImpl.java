package com.umc.workbook.repository.MemberRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc.workbook.domain.QMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    private final QMember qMember = QMember.member;
}
