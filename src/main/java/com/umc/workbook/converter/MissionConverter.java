package com.umc.workbook.converter;

import com.umc.workbook.domain.Mission;
import com.umc.workbook.dto.mission.MissionRequest;
import com.umc.workbook.dto.mission.MissionResponse;

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
}
