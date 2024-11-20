package com.umc.workbook.domain;

import com.umc.workbook.domain.commons.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity // 문의
public class Question extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // pk

    @Column(nullable = false, length = 20)
    private String questionTitle; // 문의 제목

    @Column(nullable = false)
    private String questionContent; // 문의 내용

    @Column(columnDefinition = "json")
    private String questionImage; // 문의 이미지 URL

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 멤버와 다대일 양방향

    @OneToOne(mappedBy = "question", fetch = FetchType.LAZY)
    private Answer answer; // 답변과 일대일 양방향
}
