package com.umc.workbook.repository.MissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc.workbook.domain.Mission;
import com.umc.workbook.domain.QMission;
import com.umc.workbook.domain.QRegion;
import com.umc.workbook.domain.QStore;
import com.umc.workbook.domain.enums.MissionStatus;
import com.umc.workbook.domain.mapping.QMemberMission;
import com.umc.workbook.dto.MissionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QMission mission = QMission.mission;
    private final QStore store = QStore.store;
    private final QMemberMission memberMission = QMemberMission.memberMission;
    private final QRegion region = QRegion.region;

    // memberId가 일치하는 Mission 테이블을 미션 상태에 따라 조회
    @Override
    public Page<Mission> findAllMissionsByStatusAndMemberId(Long memberId, MissionStatus missionStatus, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(memberMission.member.id.eq(memberId)); // memberId로 필터링
        builder.and(memberMission.status.eq(missionStatus)); // missionStatus로 필터링

        // 데이터 조회 쿼리 - Mission 엔티티 전체를 가져옴
        QueryResults<Mission> results = jpaQueryFactory
                .select(mission)
                .from(mission)
                .join(memberMission).on(memberMission.mission.id.eq(mission.id))
                .join(mission.store, store).fetchJoin() // store와 조인하여 데이터를 로드
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        // Page 객체 반환
        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }
}