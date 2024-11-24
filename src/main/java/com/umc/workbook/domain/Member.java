package com.umc.workbook.domain;

import com.umc.workbook.domain.commons.BaseEntity;
import com.umc.workbook.domain.enums.Gender;
import com.umc.workbook.domain.mapping.MemberMission;
import com.umc.workbook.domain.mapping.MemberPretendFood;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate //  insert와 update 시 null 인 경우는 그냥 쿼리를 보내지 않도록
@Entity // 멤버
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // pk

    @Column(nullable = false, length = 20)
    private String loginId; // 로그인 아이디

    @Column(nullable = false, length = 20)
    private String loginPassword; // 로그인 비밀번호

    @Enumerated(EnumType.STRING)
    private Gender gender; // 성별

    @Column(nullable = false, length = 10)
    private String nickName; // 닉네임

    @Column(nullable = false, length = 20)
    private String birth; // 생년월일

    @Column(nullable = false)
    private String memberAddress; // 주소

    @Column
    private String email; // 이메일

    @Column(nullable = false, length = 20)
    private String phoneNumber; // 전화번호

    @Column(columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean isPhoneVerified; // 전화번호 인증 여부

    @Column(columnDefinition = "json")
    private String profileImage; // 프로필 이미지 URL

    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer point; // 포인트

    @Column(columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean inactiveStatus; // 탈퇴 여부

    private LocalDateTime inactiveDate; // 탈퇴 신청 날짜

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberPretendFood> memberPretendFoodList = new ArrayList<>(); // 멤버 선호 음식과 일대다

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberMission> memberMissionList = new ArrayList<>(); // 멤버 미션과 일대다 양방향

    // 멤버가 사라져도 리뷰는 사라지지 않도록
    @OneToMany(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.MERGE}) // REMOVE 제외
    private List<Review> reviewList = new ArrayList<>(); // 리뷰와 일대다 양방향

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questionList = new ArrayList<>(); // 문의와 일대다 양방향

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alarm> alarmList = new ArrayList<>(); // 알림과 일대다 양방향

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AlarmSetting> alarmSettingList = new ArrayList<>(); // 알림 설정과 일대다 양방향
}
