package com.umc.workbook.domain.mapping;

import com.umc.workbook.domain.Member;
import com.umc.workbook.domain.Mission;
import com.umc.workbook.domain.commons.BaseEntity;
import com.umc.workbook.domain.enums.MissionStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity // 멤버-미션 매핑 테이블
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기본 키

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MissionStatus status = MissionStatus.CHALLENGING; // 미션 상태 (CHALLENGING, COMPLETE), 기본값 설정

    @Column(name = "expired_at")
    private LocalDateTime expiredAt; // 만료일 기본값은 현재로부터 100일 후로 설정

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // Member와 다대일 관계 (양방향)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission; // Mission과 다대일 관계 (양방향)

    // 엔티티가 저장되기 전에 만료일 기본값을 설정
    @PrePersist
    private void onCreate() {
        if (expiredAt == null) {
            expiredAt = LocalDateTime.now().plusDays(100).truncatedTo(ChronoUnit.SECONDS); // 100일 후로 기본값 설정
        }
    }
}
