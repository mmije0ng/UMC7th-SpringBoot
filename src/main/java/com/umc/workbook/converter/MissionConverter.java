package com.umc.workbook.converter;

import com.umc.workbook.domain.Mission;
import com.umc.workbook.domain.mapping.MemberMission;
import com.umc.workbook.dto.mission.MissionRequest;
import com.umc.workbook.dto.mission.MissionResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public class MissionConverter {

    // 미션 엔티티로 전환
    public static Mission toMission(MissionRequest.CreateMissionDTO request){
        return Mission.builder()
                .missionContent(request.getContent())
                .missionMoney(request.getMoney())
                .missionPoint(request.getPoint())
                .build();
    }

    // 미션 등록 응답 dto로 변환
    public static MissionResponse.CreateMissionResultDTO toCreateResultDTO(Mission mission){
        return MissionResponse.CreateMissionResultDTO
                .builder()
                .missionId(mission.getId())
                .createdAt(mission.getCreatedAt())
                .build();
    }

    // 멤버-미션 등록 응답 dto로 변환
    public static MissionResponse.CreateMemberMissionResultDTO createMemberMissionResultDTO(MemberMission memberMission){
        return MissionResponse.CreateMemberMissionResultDTO
                .builder()
                .missionContent(memberMission.getMission().getMissionContent())
                .missionStatus(memberMission.getStatus().name())
                .expiredAt(memberMission.getExpiredAt())
                .build();
    }

    // 가게 미션 조회 관련 컨버터
    public static MissionResponse.StoreMissionPreviewDTO toStoreMissionPreviewDTO(Mission mission) {
        return MissionResponse.StoreMissionPreviewDTO.builder()
                .content(mission.getMissionContent()) // 미션 내용
                .money(mission.getMissionMoney())     // 기준 금액
                .point(mission.getMissionPoint())     // 적립 포인트
                .createdAt(mission.getCreatedAt())    // 생성일자
                .build();
    }

    // 가게 미션 목록 조회 관련 컨버터
    public static MissionResponse.StoreMissionPreViewListDTO toStoreMissionPreViewListDTO(Page<Mission> storeMissionPage, String storeName){
        // Mission 리스트를 StoreMissionPreviewDTO로 변환
        List<MissionResponse.StoreMissionPreviewDTO> storeMissionList = storeMissionPage.stream()
                .map(MissionConverter::toStoreMissionPreviewDTO) // Misson을 StoreMissionPreviewDTO로 변환
                .toList();

        return MissionResponse.StoreMissionPreViewListDTO.builder()
                .missionList(storeMissionList)
                .listSize(storeMissionList.size())
                .totalPage(storeMissionPage.getTotalPages())
                .totalElements(storeMissionPage.getTotalElements())
                .isFirst(storeMissionPage.isFirst())
                .isLast(storeMissionPage.isLast())
                .storeName(storeName)
                .build();
    }
}
