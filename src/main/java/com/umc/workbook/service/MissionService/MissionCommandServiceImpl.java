package com.umc.workbook.service.MissionService;

import com.umc.workbook.converter.MissionConverter;
import com.umc.workbook.domain.Mission;
import com.umc.workbook.domain.Store;
import com.umc.workbook.dto.mission.MissionRequest;
import com.umc.workbook.dto.mission.MissionResponse;
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
}
