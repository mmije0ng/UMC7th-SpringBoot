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
}
