package com.umc.workbook.controller;

import com.umc.workbook.apiPayload.ApiResponse;
import com.umc.workbook.domain.enums.MissionStatus;
import com.umc.workbook.dto.mission.MissionDto;
import com.umc.workbook.dto.mission.MissionRequest;
import com.umc.workbook.dto.mission.MissionResponse;
import com.umc.workbook.service.MissionService.MissionCommandService;
import com.umc.workbook.service.MissionService.MissionQueryService;
import com.umc.workbook.validation.annotation.ExistStore;
import com.umc.workbook.validation.annotation.ValidMissionStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mission")
public class MissionController {
    private final MissionQueryService missionQueryService;
    private final MissionCommandService missionCommandService;

    // 가게에 미션 추가
    @PostMapping
    ApiResponse<MissionResponse.CreateMissionResultDTO> createMission (@RequestParam(name = "storeId") @ExistStore Long storeId,
                                                                       @RequestBody @Valid MissionRequest.CreateMissionDTO request){
        MissionResponse.CreateMissionResultDTO response = missionCommandService.addMission(storeId, request);
        return ApiResponse.onSuccess(response);
    }

    // 가게의 미션을 도전중인 미션에 추가
    @PostMapping("/challenging")
    @Operation(
            summary = "미션 상태를 도전 중으로 변경",
            description = "status, missionId, memberId를 쿼리 파라미터로 받아 미션 상태를 도전 중으로 변경",
            parameters = {
                    @Parameter(name = "missionId", description = "미션 ID", example = "4"),
                    @Parameter(name = "memberId", description = "멤버 ID", example = "1")
            }
    )
    public ApiResponse<?> createMissionStatusChallenging(
            @RequestParam @ValidMissionStatus Map<String, String> params) {

        MissionResponse.CreateMemberMissionResultDTO result = missionCommandService.addMemberMission(Long.parseLong(params.get("missionId")), Long.parseLong(params.get("memberId")));
        return ApiResponse.onSuccess(result);
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
