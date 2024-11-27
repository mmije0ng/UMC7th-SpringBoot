package com.umc.workbook.repository.MemberRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc.workbook.domain.QMember;
import com.umc.workbook.dto.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final QMember qMember = QMember.member;

    // memberId로 마이페이지 조회
    @Override
    public Optional<MemberResponse.MyPageDTO> findMemberByMemberIdForMyPage(Long memberId) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qMember.id.eq(memberId)); // memberId로 필터링

        // 데이터 조회
        Tuple result = jpaQueryFactory
                .select(
                        qMember.nickName,
                        qMember.email,
                        qMember.isPhoneVerified,
                        qMember.phoneNumber,
                        qMember.point
                )
                .from(qMember)
                .where(builder)
                .fetchOne();

        if (result == null) {
            return Optional.empty();
        }

        // 조회된 데이터를 MemberDto.MyPage로 매핑
        MemberResponse.MyPageDTO memberData = new MemberResponse.MyPageDTO(
                result.get(qMember.nickName),
                result.get(qMember.email),
                result.get(qMember.isPhoneVerified),
                result.get(qMember.phoneNumber),
                result.get(qMember.point)
        );

        return Optional.of(memberData);
    }
}