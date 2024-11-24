package com.umc.workbook.service.MemberService;

import com.umc.workbook.apiPayload.code.status.ErrorStatus;
import com.umc.workbook.apiPayload.exception.handler.FoodCategoryHandler;
import com.umc.workbook.converter.MemberConverter;
import com.umc.workbook.converter.MemberPretendFoodConverter;
import com.umc.workbook.domain.FoodCategory;
import com.umc.workbook.domain.Member;
import com.umc.workbook.domain.mapping.MemberPretendFood;
import com.umc.workbook.dto.MemberRequest;
import com.umc.workbook.repository.FoodCategoryRepository.FoodCategoryRepository;
import com.umc.workbook.repository.MemberRepository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    // 회원가입
    @Override
    @Transactional
    public Member joinMember(MemberRequest.JoinDTO request) {

        // 컨버터로 멤버 엔티티 생성
        Member member = MemberConverter.toMember(request);

        // 요청 dto로 넘어온 음식 카테고리 아이디와 일치하는 FoodCategory를 리스트로
        List<FoodCategory> foodCategoryList = request.getMemberPretendFoodList().stream()
                .map(foodCategoryId -> foodCategoryRepository.findById(foodCategoryId)
                        .orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND)))
                .collect(Collectors.toList());

        // 멤버 - 음식 카테고리 엔티티에 음식 카테고리 매핑
        List<MemberPretendFood> memberPretendFoodList = MemberPretendFoodConverter.toMemberPretendFoodList(foodCategoryList);

        // 멤버 - 음식 카테고리 엔티티에 멤버 매핑
        // 새로 회원가입한 멤버를 set
        memberPretendFoodList.forEach(memberPretendFood -> {memberPretendFood.setMember(member);});

        // 멤버 저장
        return memberRepository.save(member);
    }
}
