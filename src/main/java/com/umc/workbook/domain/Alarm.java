package com.umc.workbook.domain;

import com.umc.workbook.domain.commons.BaseEntity;
import com.umc.workbook.domain.enums.AlarmType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity // 알림
public class Alarm extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // pk

    @Enumerated(EnumType.STRING)
    private AlarmType alarmType; // 알림 타입

    @Column(nullable = false, length = 50)
    private String alarmContent; // 알림 설명

    @Column(columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean isRead; // 알림 읽음 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 멤버와 다대일 양방향
}
