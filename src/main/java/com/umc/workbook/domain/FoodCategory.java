package com.umc.workbook.domain;

import com.umc.workbook.domain.commons.BaseEntity;
import com.umc.workbook.domain.mapping.MemberPretendFood;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class FoodCategory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // pk

    @Column(nullable = false, length = 20)
    private String foodCategoryName; // 음식 카테고리 이름

    // MemberPretendFood가 삭제되더라도 FoodCategory는 삭제되지 않도록
    @OneToMany(mappedBy = "foodCategory", cascade = {CascadeType.PERSIST, CascadeType.MERGE}) // REMOVE 제외
    private List<MemberPretendFood> memberPretendFoods = new ArrayList<>(); // 멤버 선호 음식과 일대다 양방향
}
