package com.umc.workbook.service.MissionService;

import com.umc.workbook.domain.enums.MissionStatus;
import com.umc.workbook.dto.MissionDto;
import org.springframework.data.domain.Page;

public interface MissionQueryService {
    // 페이지네이션이 적용된, memberId, 미션 상태로 조회한 Mission 테이블 가져오기
    Page<MissionDto.MissionStatusResponse> pagedMissionsByMemberIdAndMissionStatus(Long memberId, MissionStatus missionStatus, Integer pageNumber);
}
