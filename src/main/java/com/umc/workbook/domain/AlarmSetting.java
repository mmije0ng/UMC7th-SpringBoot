package com.umc.workbook.domain;

import com.umc.workbook.domain.commons.BaseEntity;
import com.umc.workbook.domain.enums.AlarmType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity // 알림 설정
public class AlarmSetting extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // pk

    @Enumerated(EnumType.STRING)
    private AlarmType alarmType; // 알림 타입

    @Column(columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean isAlarmSet; // 알림 설정 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 멤버와 다대일 양방향
}
