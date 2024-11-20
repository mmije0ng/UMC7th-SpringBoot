package com.umc.workbook.domain.mapping;

import com.umc.workbook.domain.commons.BaseEntity;
import com.umc.workbook.domain.FoodCategory;
import com.umc.workbook.domain.Member;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity // 멤버 - 음식 카테고리 매핑
public class MemberPretendFood extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // pk

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member; // 멤버와 다대일 양방향

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="food_category_id")
    private FoodCategory foodCategory; // 음식 카테고리와 다대일 양방향
}
