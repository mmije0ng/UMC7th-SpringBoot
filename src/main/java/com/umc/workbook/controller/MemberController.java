package com.umc.workbook.controller;

import com.umc.workbook.dto.MissionDto;
import com.umc.workbook.service.MemberService.MemberQueryService;
import com.umc.workbook.service.MissionService.MissionQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/member")
public class MemberController {
    private final MemberQueryService memberQueryService;

    @Autowired
    public MemberController(MemberQueryService memberQueryService) {
        this.memberQueryService = memberQueryService;
    }

    // 마이페이지 조회
    // localhost:8080/api/member/{memberId}
    @GetMapping("/{memberId}")
    public ResponseEntity<?> getChallengingMissionPage(@PathVariable(name="memberId") Long memberId) throws Exception {
        try {
            return ResponseEntity.ok(memberQueryService.findMemberByForMyPage(memberId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}
