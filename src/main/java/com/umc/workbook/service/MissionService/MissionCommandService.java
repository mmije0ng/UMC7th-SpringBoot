package com.umc.workbook.service.MissionService;

import com.umc.workbook.dto.mission.MissionRequest;
import com.umc.workbook.dto.mission.MissionResponse;

public interface MissionCommandService {
    MissionResponse.CreateMissionResultDTO addMission(Long storeId, MissionRequest.CreateMissionDTO request);
}
