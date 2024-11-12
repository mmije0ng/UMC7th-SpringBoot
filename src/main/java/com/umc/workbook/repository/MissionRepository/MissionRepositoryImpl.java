package com.umc.workbook.repository.MissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc.workbook.domain.Mission;
import com.umc.workbook.domain.QMission;
import com.umc.workbook.domain.QStore;
import com.umc.workbook.domain.enums.MissionStatus;
import com.umc.workbook.domain.mapping.QMemberMission;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QMission mission = QMission.mission;
    private final QStore store = QStore.store;
    private final QMemberMission memberMission = QMemberMission.memberMission;

    @Override
    public Page<Mission> findAllMissionsByStatusAndMemberId(Long memberId, MissionStatus missionStatus, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(memberMission.member.id.eq(memberId)); // memberId로 필터링
        builder.and(memberMission.status.eq(missionStatus)); // missionStatus로 필터링

        // 기본 쿼리
        List<Mission> missions = jpaQueryFactory
                .selectFrom(mission)
                .join(memberMission).on(memberMission.mission.id.eq(mission.id))
                .join(mission.store, store).fetchJoin() // store와 조인하여 데이터를 로드
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 전체 카운트 쿼리
        long total = jpaQueryFactory
                .selectFrom(mission)
                .join(memberMission).on(memberMission.mission.id.eq(mission.id))
                .where(builder)
                .fetchCount();

        return new PageImpl<>(missions, pageable, total);
    }
}