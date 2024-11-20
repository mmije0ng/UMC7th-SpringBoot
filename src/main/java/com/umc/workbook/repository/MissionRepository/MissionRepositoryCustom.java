package com.umc.workbook.repository.MissionRepository;

import com.umc.workbook.domain.Mission;
import com.umc.workbook.domain.enums.MissionStatus;
import com.umc.workbook.dto.MissionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MissionRepositoryCustom {
    // memberId가 일치하는 Mission 테이블을 미션 상태에 따라 가져오기
    Page<Mission> findAllMissionsByStatusAndMemberId(Long memberId, MissionStatus missionStatus, Pageable pageable);

    // 미션 만료일과 함께 조회
    Page<MissionDto.DdayResponse> findAllMissionsByMemberIdWithDday(Long memberId, String regionName, MissionStatus missionStatus, Pageable pageable);
}
