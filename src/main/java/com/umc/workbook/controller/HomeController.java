package com.umc.workbook.controller;

import com.umc.workbook.dto.mission.MissionDto;
import com.umc.workbook.service.MissionService.MissionQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/home")
public class HomeController {
    private final MissionQueryService missionQueryService;

    @Autowired
    public HomeController(MissionQueryService missionQueryService) {
        this.missionQueryService = missionQueryService;

    }

    // 멤버별 진행중인 미션 불러오기
    // localhost:8080/api/home?memberId={memberId}&regionName={regionName}&pageNumber={pageNumber}
    @GetMapping("")
    public ResponseEntity<?> getChallengingMissionPage(@RequestParam(name="memberId") Long memberId,
                                                       @RequestParam(name="regionName") String regionName,
                                                       @RequestParam(name="pageNumber") Integer pageNumber) throws Exception {
        try {
            MissionDto.PagedHomeResponse responses = missionQueryService.pagedMissionsByMemberIdWithDday(memberId, regionName, pageNumber);
            return ResponseEntity.ok(responses);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}
