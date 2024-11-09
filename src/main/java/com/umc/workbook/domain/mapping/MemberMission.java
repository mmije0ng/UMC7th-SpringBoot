package com.umc.workbook.domain.mapping;

import com.umc.workbook.domain.commons.BaseEntity;
import com.umc.workbook.domain.Member;
import com.umc.workbook.domain.Mission;
import com.umc.workbook.domain.enums.MissionStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity // 멤버-미션 메핑 테이블
public class MemberMission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // pk

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(255) DEFAULT 'CHALLENGING'")
    private MissionStatus status; // 미션 진행 여부 (도전중, 완료)

    @ManyToOne(fetch = FetchType.LAZY) // 프록시에서 조회
    @JoinColumn(name = "member_id")
    private Member member; // 멤버와 다대일 양방향

    @ManyToOne(fetch = FetchType.LAZY) // 프록시에서 조회
    @JoinColumn(name = "mission_id")
    private Mission mission; // 미션과 다대일 양방향
}
