package com.umc.workbook.domain;

import com.umc.workbook.domain.commons.BaseEntity;
import com.umc.workbook.domain.mapping.MemberMission;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity // 미션
public class Mission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // pk

    @Column(nullable = false)
    private String missionContent;

    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer missionMoney; // 기준 금액

    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer missionPoint; // 적립 포인트

    // MemberMission이 삭제되더라도 Mission은 삭제되지 않도록
    @OneToMany(mappedBy = "mission", cascade = {CascadeType.PERSIST, CascadeType.MERGE}) // REMOVE 제외
    private List<MemberMission> memberMissionList = new ArrayList<>(); // 멤버 미션과 일대다 양방향

    // Store가 변경되면 Mission도 변경되도록
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id")
    private Store store; // 가게와 다대일 단방향
}
