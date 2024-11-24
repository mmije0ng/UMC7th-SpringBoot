package com.umc.workbook.converter;

import com.umc.workbook.domain.FoodCategory;
import com.umc.workbook.domain.mapping.MemberPretendFood;

import java.util.List;
import java.util.stream.Collectors;

public class MemberPretendFoodConverter {

    // 멤버 - 음식 카테고리 매핑 테이블 생성
    // 리스트로 넘어온 FoodCategory에 대한 멤버-음식 카테고리 엔티티 리스트 생성
    public static List<MemberPretendFood> toMemberPretendFoodList(List<FoodCategory> foodCategoryList ){
        return foodCategoryList.stream()
                .map(foodCategory -> MemberPretendFood.builder()
                                .foodCategory(foodCategory)
                                .build()).collect(Collectors.toList());
    }
}
