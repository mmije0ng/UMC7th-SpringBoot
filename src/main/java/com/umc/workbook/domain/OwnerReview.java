package com.umc.workbook.domain;

import com.umc.workbook.domain.commons.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class OwnerReview extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // pk

    @Column(nullable = false)
    private String ownerReviewContent; // 사장님 답글 내용
}
