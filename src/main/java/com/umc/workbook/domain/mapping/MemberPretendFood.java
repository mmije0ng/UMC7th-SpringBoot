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

    public void setMember(Member member){
        // this.member: 현재 MemberPretendFood 엔티티가 이미 참조하고 있는 객체
        // 양뱡향 연관관계에서 Member와 MemberPretendFood가 서로 같은 엔티티를 참조하도록
        if(this.member!=null)
            member.getMemberPretendFoodList().remove(this);

        this.member = member;

        // 멤버 엔티티에 멤버-음식 카테고리 리스트 추가 (양방향 매핑이기 때문)
        member.getMemberPretendFoodList().add(this);
    }

    public void setFoodCategory(FoodCategory foodCategory){
        this.foodCategory = foodCategory;
    }
}
