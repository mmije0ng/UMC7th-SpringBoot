package com.umc.workbook.service.MissionService;

import com.umc.workbook.converter.MissionConverter;
import com.umc.workbook.domain.Member;
import com.umc.workbook.domain.Mission;
import com.umc.workbook.domain.Store;
import com.umc.workbook.domain.enums.MissionStatus;
import com.umc.workbook.domain.mapping.MemberMission;
import com.umc.workbook.dto.mission.MissionDto;
import com.umc.workbook.dto.mission.MissionResponse;
import com.umc.workbook.repository.MemberMissionRepository.MemberMissionRepository;
import com.umc.workbook.repository.MemberRepository.MemberRepository;
import com.umc.workbook.repository.MissionRepository.MissionRepository;
import com.umc.workbook.repository.StoreRepository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {
    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    private static final Integer PAGE_SIZE=10;

    // 페이지 정렬, 최신순
    private Pageable pageRequest(Integer pageNumber) {
        return PageRequest.of(pageNumber, PAGE_SIZE);
    }

    // 미션 상태별로 미션 조회
    @Override
    public Page<MissionDto.StatusResponse> pagedMissionsByMemberIdAndMissionStatus(Long memberId, MissionStatus missionStatus, Integer pageNumber) {
        // memberId가 일치하는 Mission 테이블을 미션 상태에 따라 가져오기
        Page<Mission> pagedMissions = missionRepository.findAllMissionsByStatusAndMemberId(memberId, missionStatus, pageRequest(pageNumber));

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
                memberId, regionName, MissionStatus.CHALLENGING, pageRequest(pageNumber));

        // Page 객체로 반환
        return new MissionDto.PagedHomeResponse(member.getPoint(), pagedMissions);
    }

    // 가게의 미션 목록 조회 (페이지네이션 적용)
    @Override
    public MissionResponse.StoreMissionPreViewListDTO getStoreMissionPage(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId).get();

        // 가게 아이디와 일치하는 미션 페이지 조회
        Page<Mission> storeMissionPage = missionRepository.findAllMissionByStoreId(storeId, pageRequest(page));

        return MissionConverter.toStoreMissionPreViewListDTO(storeMissionPage, store.getStoreName());
    }

    // 멤버의 미션 상태에 따른 미션 목록 조회 (페이지네이션 적용)
    @Override
    public MissionResponse.MemberMissionPreViewListDTO getMemberMissionPage(Long memberId, String status, Integer page) {
        Member member = memberRepository.findById(memberId).get();
        // 멤버, 미션 상태와 일치하는 멤버-미션 페이지 조회
        Page<MemberMission> memberMissionPage = memberMissionRepository.findAllMemberMissionsByMemberAndStatus(member, MissionStatus.valueOf(status.toUpperCase()), pageRequest(page));

        return MissionConverter.toMemberMissionPreViewListDTO(memberMissionPage);
    }
}
