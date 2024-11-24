package com.umc.workbook.controller;

import com.umc.workbook.domain.enums.MissionStatus;
import com.umc.workbook.dto.mission.MissionDto;
import com.umc.workbook.service.MissionService.MissionQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/mission")
public class MissionController {
    private final MissionQueryService missionQueryService;

    @Autowired
    public MissionController(MissionQueryService missionQueryService) {
        this.missionQueryService = missionQueryService;

    }

    // 멤버별 진행중인 미션 불러오기
    // localhost:8080/api/mission/challenging?memberId={memberId}&pageNumber={pageNumber}
    @GetMapping("/challenging")
    public ResponseEntity<?> getChallengingMissionPage(@RequestParam(name="memberId") Long memberId,
                                                    @RequestParam(name="pageNumber") Integer pageNumber) throws Exception {
        try {
            Page<MissionDto.StatusResponse> challenginMissionPage = missionQueryService.pagedMissionsByMemberIdAndMissionStatus(memberId, MissionStatus.CHALLENGING, pageNumber);
            return ResponseEntity.ok(challenginMissionPage);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    // 멤버별 진행완료된 미션 불러오기
    // localhost:8080/api/mission/complete?memberId={memberId}&pageNumber={pageNumber}
    @GetMapping("/complete")
    public ResponseEntity<?> getCompleteMissionPage(@RequestParam(name="memberId") Long memberId,
                                                       @RequestParam(name="pageNumber") Integer pageNumber) throws Exception {
        try {
            Page<MissionDto.StatusResponse> completeMissionPage = missionQueryService.pagedMissionsByMemberIdAndMissionStatus(memberId, MissionStatus.COMPLETE, pageNumber);
            return ResponseEntity.ok(completeMissionPage);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}
