package com.umc.workbook.service.MissionService;

import com.umc.workbook.domain.Mission;
import com.umc.workbook.domain.enums.MissionStatus;
import com.umc.workbook.domain.mapping.MemberMission;
import com.umc.workbook.dto.mission.MissionDto;
import com.umc.workbook.dto.mission.MissionResponse;
import org.springframework.data.domain.Page;

public interface MissionQueryService {
    // 페이지네이션이 적용된, memberId, 미션 상태로 조회한 Mission 테이블 가져오기
    Page<MissionDto.StatusResponse> pagedMissionsByMemberIdAndMissionStatus(Long memberId, MissionStatus missionStatus, Integer pageNumber);

    // 홈 화면 조회
    MissionDto.PagedHomeResponse pagedMissionsByMemberIdWithDday(Long memberId, String regionName, Integer pageNumber);

    // 가게의 미션 목록 조회 (페이지네이션 적용)
    MissionResponse.StoreMissionPreViewListDTO getStoreMissionPage(Long storeId, Integer page);
}
