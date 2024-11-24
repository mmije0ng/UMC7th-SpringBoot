package com.umc.workbook.domain;

import com.umc.workbook.domain.commons.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // pk

    @Column(nullable = false)
    private Double reviewScore; // 리뷰 평점

    @Column(nullable = false)
    private String reviewContent; // 리뷰 내용

    @Column(columnDefinition = "json")
    private String reviewImage; // 리뷰 이미지

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 멤버와 다대일

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store; // 가게와 다대일

    // 멤버와 양방향 연관관계 추가
    public void setMember(Member member){
        // 기존 연관된 멤버에서 현재 객체 제거
        if (this.member != null)
            this.member.getReviewList().remove(this);

        // 새로운 멤버 설정
        this.member = member;

        // 새로운 멤버의 리뷰 리스트에 현재 객체 추가
        if (member != null)
            member.getReviewList().add(this);
    }

    // 가게와 양방향 연관관계 추가
    public void setStore(Store store){
        // 기존 연관된 가게에서 현재 객체 제거
        if (this.store != null)
            this.store.getReviewList().remove(this);

        // 새로운 가게 설정
        this.store = store;

        // 새로운 가게의 리뷰 리스트에 현재 객체 추가
        if (store != null)
            store.getReviewList().add(this);
    }

}
