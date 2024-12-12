package com.umc.workbook.controller;

import com.umc.workbook.apiPayload.ApiResponse;
import com.umc.workbook.domain.enums.MissionStatus;
import com.umc.workbook.dto.mission.MissionDto;
import com.umc.workbook.dto.mission.MissionRequest;
import com.umc.workbook.dto.mission.MissionResponse;
import com.umc.workbook.service.MissionService.MissionCommandService;
import com.umc.workbook.service.MissionService.MissionQueryService;
import com.umc.workbook.validation.annotation.CheckPage;
import com.umc.workbook.validation.annotation.ExistStore;
import com.umc.workbook.validation.annotation.ValidMissionStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    // 특정 가게의 미션 목록 조회
    @GetMapping
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "가게의 모든 미션 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다."),
            @Parameter(name = "page", description = "페이지 번호, query parameter 입니다.")
    })
    ApiResponse<MissionResponse.StoreMissionPreViewListDTO> getStoreMissionPage (@RequestParam(name = "storeId") @ExistStore Long storeId, @CheckPage Integer page){
        MissionResponse.StoreMissionPreViewListDTO response = missionQueryService.getStoreMissionPage(storeId, page);

        return ApiResponse.onSuccess(response);
    }
}
