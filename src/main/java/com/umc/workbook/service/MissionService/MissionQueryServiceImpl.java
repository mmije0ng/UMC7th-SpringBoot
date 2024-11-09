package com.umc.workbook.service.MissionService;

import com.umc.workbook.domain.Mission;
import com.umc.workbook.domain.enums.MissionStatus;
import com.umc.workbook.dto.MissionDto;
import com.umc.workbook.repository.MissionRepository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {
    private final MissionRepository missionRepository;

    private static final int PAGE_SIZE = 3; // 한 페이지에 표시할 데이터 수

    @Override
    public Page<MissionDto.MissionStatusResponse> pagedMissionsByMemberIdAndMissionStatus(Long memberId, MissionStatus missionStatus, Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE);

        // memberId가 일치하는 Mission 테이블을 미션 상태에 따라 가져오기
        Page<Mission> pagedMissions = missionRepository.findAllMissionsByStatusAndMemberId(memberId, missionStatus, pageable);

        // Mission 엔티티를 MissionStatusResponse DTO로 매핑
        Page<MissionDto.MissionStatusResponse> result = pagedMissions.map(mission -> MissionDto.MissionStatusResponse.builder()
                .missionContent(mission.getMissionContent())
                .missionMoney(mission.getMissionMoney())
                .missionPoint(mission.getMissionPoint())
                .missionStatus(missionStatus.name()) // 미션 진행 상태
                .storeName(mission.getStore().getStoreName()) // 가게 이름
                .build());

        return result;
    }
}
