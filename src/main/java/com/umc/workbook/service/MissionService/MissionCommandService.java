package com.umc.workbook.service.MissionService;

import com.umc.workbook.dto.mission.MissionRequest;
import com.umc.workbook.dto.mission.MissionResponse;

public interface MissionCommandService {
    // 미션 추가
    MissionResponse.CreateMissionResultDTO addMission(Long storeId, MissionRequest.CreateMissionDTO request);

    // 미션을 도전중인 미션에 추가 (멤버-미션 테이블 생성)
    MissionResponse.CreateMemberMissionResultDTO addMemberMission(Long missionId, Long memberId);

    // 진행중인 미션을 완료로 변경
    MissionResponse.UpdateMissionStatusDTO setMemberMissionStatus(Long memberId, Long memberMissionId);
}
