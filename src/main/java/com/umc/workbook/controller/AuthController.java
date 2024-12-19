package com.umc.workbook.controller;

import com.umc.workbook.apiPayload.ApiResponse;
import com.umc.workbook.converter.MemberConverter;
import com.umc.workbook.domain.Member;
import com.umc.workbook.dto.member.MemberRequest;
import com.umc.workbook.dto.member.MemberResponse;
import com.umc.workbook.service.MemberService.MemberCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final MemberCommandService memberCommandService;

    // 회원가입
    @PostMapping("/signup")
    public ApiResponse <MemberResponse.SignUpResultDTO> join(@RequestBody @Valid MemberRequest.SignUpDTO request){
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toSignUpResultDTO(member));
    }
}
