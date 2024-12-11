package com.umc.workbook.service.MissionService;

import com.umc.workbook.domain.Member;
import com.umc.workbook.domain.Mission;
import com.umc.workbook.domain.enums.MissionStatus;
import com.umc.workbook.dto.mission.MissionDto;
import com.umc.workbook.repository.MemberRepository.MemberRepository;
import com.umc.workbook.repository.MissionRepository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    // 페이지 정렬, 최신순
    private Pageable pageRequest(Integer pageNumber, Integer pageSize) {
        return PageRequest.of(pageNumber, pageSize, Sort.by("createdAt").descending());
    }

    // 미션 상태별로 미션 조회
    @Override
    public Page<MissionDto.StatusResponse> pagedMissionsByMemberIdAndMissionStatus(Long memberId, MissionStatus missionStatus, Integer pageNumber) {
        // memberId가 일치하는 Mission 테이블을 미션 상태에 따라 가져오기
        Page<Mission> pagedMissions = missionRepository.findAllMissionsByStatusAndMemberId(memberId, missionStatus, pageRequest(pageNumber, 3));

        // Mission 엔티티를 MissionStatusResponse DTO로 매핑
        Page<MissionDto.StatusResponse> result = pagedMissions.map(mission -> MissionDto.StatusResponse.builder()
                .missionContent(mission.getMissionContent())
                .missionMoney(mission.getMissionMoney())
                .missionPoint(mission.getMissionPoint())
                .missionStatus(missionStatus.name()) // 미션 진행 상태
                .storeName(mission.getStore().getStoreName()) // 가게 이름
                .build());

        return result;
    }

    // 홈화면 조회 - 미션별 디데이와 함께
    @Override
    public MissionDto.PagedHomeResponse pagedMissionsByMemberIdWithDday(Long memberId, String regionName, Integer pageNumber) {
        // memberId에 해당하는 Member 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member가 존재하지 않습니다."));

        // memberId가 일치하는 Mission 테이블을 미션 상태에 따라 가져오기
        Page<MissionDto.DdayResponse> pagedMissions = missionRepository.findAllMissionsByMemberIdWithDday(
                memberId, regionName, MissionStatus.CHALLENGING, pageRequest(pageNumber, 10));

        // Page 객체로 반환
        return new MissionDto.PagedHomeResponse(member.getPoint(), pagedMissions);
    }
}
