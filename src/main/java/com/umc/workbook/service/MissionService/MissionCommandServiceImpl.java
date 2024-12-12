package com.umc.workbook.service.MissionService;

import com.umc.workbook.apiPayload.code.status.ErrorStatus;
import com.umc.workbook.apiPayload.exception.handler.MemberHandler;
import com.umc.workbook.apiPayload.exception.handler.MissionHandler;
import com.umc.workbook.converter.MemberMissionConverter;
import com.umc.workbook.converter.MissionConverter;
import com.umc.workbook.domain.Member;
import com.umc.workbook.domain.Mission;
import com.umc.workbook.domain.Store;
import com.umc.workbook.domain.enums.MissionStatus;
import com.umc.workbook.domain.mapping.MemberMission;
import com.umc.workbook.dto.mission.MissionRequest;
import com.umc.workbook.dto.mission.MissionResponse;
import com.umc.workbook.repository.MemberMissionRepository.MemberMissionRepository;
import com.umc.workbook.repository.MemberRepository.MemberRepository;
import com.umc.workbook.repository.MissionRepository.MissionRepository;
import com.umc.workbook.repository.StoreRepository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MissionCommandServiceImpl implements MissionCommandService{

    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public MissionResponse.CreateMissionResultDTO addMission(Long storeId, MissionRequest.CreateMissionDTO request) {
        Store store = storeRepository.findById(storeId).get(); // 가게 조회

        Mission mission = MissionConverter.toMission(request); // 미션 엔티티 생성
        mission.setStore(store); // 가게와 연관관계 설정
        missionRepository.save(mission); // 엔티티 등록

        return MissionConverter.toCreateResultDTO(mission);
    }

    @Override
    @Transactional
    public MissionResponse.CreateMemberMissionResultDTO addMemberMission(Long missionId, Long memberId) {
        // 미션 검증
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        // status가 challenging인 멤버 미션 엔티티
        MemberMission memberMission = MemberMissionConverter.toMemberMission();

        // 양방향 연관관계 설정
        memberMission.setMember(member);
        memberMission.setMission(mission);

        memberMissionRepository.save(memberMission);

        return MissionConverter.createMemberMissionResultDTO(memberMission);
    }

    // 진행중인 미션을 완료로 변경
    @Transactional
    @Override
    public MissionResponse.UpdateMissionStatusDTO setMemberMissionStatus(Long memberId, Long memberMissionId) {
        // 존재하지 않은 멤버-미션일 경우 에러 반환
        MemberMission memberMission = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        // 멤버의 멤버-미션이 아닐 경우 에러 반환
        if(!memberMission.getMission().getId().equals(memberId))
            throw new MissionHandler(ErrorStatus.MISSION_NOT_EQUAL_MEMBER);

        // 이미 도전중인 미션일 경우 에러 반환
        if(memberMission.getStatus().equals(MissionStatus.COMPLETE))
            throw new MissionHandler(ErrorStatus.MISSION_ALREADY_COMPLETE);

        memberMission.setStatus(MissionStatus.COMPLETE);

        return MissionConverter.toUpdateMissionStatusDTO(memberMission);
    }
}
