package com.umc.workbook.repository.MissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc.workbook.config.QueryDSLSortUtil;
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
    private final QMission qMission = QMission.mission;
    private final QStore qStore = QStore.store;
    private final QMemberMission qMemberMission = QMemberMission.memberMission;
    private final QRegion qRegion = QRegion.region;

    // memberId가 일치하는 Mission 테이블을 미션 상태에 따라 조회
    @Override
    public Page<Mission> findAllMissionsByStatusAndMemberId(Long memberId, MissionStatus missionStatus, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qMemberMission.member.id.eq(memberId)); // memberId로 필터링
        builder.and(qMemberMission.status.eq(missionStatus)); // missionStatus로 필터링

        // 데이터 조회 쿼리 - Mission 엔티티 전체를 가져옴
        QueryResults<Mission> results = jpaQueryFactory
                .select(qMission)
                .from(qMission)
                .join(qMemberMission).on(qMemberMission.mission.id.eq(qMission.id))
                .join(qMission.store, qStore).fetchJoin() // store와 조인하여 데이터를 로드
                .where(builder)
                .orderBy(QueryDSLSortUtil.getSort(pageable, new PathBuilder<>(Mission.class, "mission"))) // 최신순 정렬
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        // Page 객체 반환
        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    // 미션 만료일과 함께 조회
    @Override
    public Page<MissionDto.DdayResponse> findAllMissionsByMemberIdWithDday(Long memberId, String regionName, MissionStatus missionStatus, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qMemberMission.member.id.eq(memberId)); // memberId로 필터링
        builder.and(qMemberMission.status.eq(missionStatus)); // missionStatus로 필터링
        builder.and(qRegion.regionName.eq(regionName)); // regionName으로 필터링

        // D-Day 계산을 위한 SQL 함수 표현
        NumberTemplate<Integer> dDay = Expressions.numberTemplate(Integer.class, "DATEDIFF({0}, {1})", qMemberMission.expiredAt, qMemberMission.createdAt);

        // 데이터 조회 쿼리
        QueryResults<Tuple> results = jpaQueryFactory
                .select(
                        qMission.missionContent,
                        qMission.missionMoney,
                        qMission.missionPoint,
                        qMemberMission.status,
                        qStore.storeName,
                        dDay // D-Day 계산된 값
                )
                .from(qMission)
                .join(qMemberMission).on(qMemberMission.mission.id.eq(qMission.id)) // 멤버 미션과 조인
                .join(qMission.store, qStore) // store와 조인
                .join(qStore.region, qRegion) // region과 조인
                .where(builder)
                .orderBy(QueryDSLSortUtil.getSort(pageable, new PathBuilder<>(Mission.class, "mission"))) // 최신순 정렬
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        // Tuple을 DTO로 변환
        List<MissionDto.DdayResponse> missions = results.getResults().stream()
                .map(tuple -> MissionDto.DdayResponse.builder()
                        .missionContent(tuple.get(qMission.missionContent))        // 미션 내용
                        .missionMoney(tuple.get(qMission.missionMoney))            // 미션 기준 금액
                        .missionPoint(tuple.get(qMission.missionPoint))            // 미션 적립 포인트
                        .missionStatus(tuple.get(qMemberMission.status).name())    // 미션 진행 상태
                        .storeName(tuple.get(qStore.storeName))                    // 가게 이름
                        .dDay(tuple.get(dDay).toString())                         // 디데이 값 문자열로 변환
                        .build())
                .collect(Collectors.toList());

        // 페이지 객체 반환
        return new PageImpl<>(missions, pageable, results.getTotal());
    }

}