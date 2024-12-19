package com.umc.workbook.controller;

import com.umc.workbook.dto.member.MemberRequest;
import com.umc.workbook.service.MemberService.MemberCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MemberViewController {
    private final MemberCommandService memberCommandService;

    // 로그인
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // 회원가입
    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("memberSignUpDTO", new MemberRequest.SignUpDTO());
        return "signup";
    }

    @PostMapping("/members/signup")
    public String signUp(@ModelAttribute("memberSignUpDTO") @Valid MemberRequest.SignUpDTO request,
                         BindingResult bindingResult,
                         Model model){
        if (bindingResult.hasErrors()) {
            log.error("signup 페이지 뷰 바인딩 실패");
            log.error(bindingResult.getAllErrors().toString());
            // 뷰에 데이터 바인딩이 실패할 경우 signup 페이지를 유지
            return "signup";
        }

        try {
            memberCommandService.joinMember(request);
            log.info("signup 회원가입 성공");

            return "redirect:/login";
        } catch (Exception e) {
            // 회원가입 과정에서 에러가 발생할 경우 에러 메시지를 보내고, signup 페이디를 유지
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
    }

    // 홈화면
    @GetMapping("/home")
    public String home() {
        return "home";
    }

    // 관리자 페이지
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}