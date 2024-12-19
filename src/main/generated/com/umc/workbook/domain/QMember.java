package com.umc.workbook.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 658012864L;

    public static final QMember member = new QMember("member1");

    public final com.umc.workbook.domain.commons.QBaseEntity _super = new com.umc.workbook.domain.commons.QBaseEntity(this);

    public final ListPath<Alarm, QAlarm> alarmList = this.<Alarm, QAlarm>createList("alarmList", Alarm.class, QAlarm.class, PathInits.DIRECT2);

    public final ListPath<AlarmSetting, QAlarmSetting> alarmSettingList = this.<AlarmSetting, QAlarmSetting>createList("alarmSettingList", AlarmSetting.class, QAlarmSetting.class, PathInits.DIRECT2);

    public final StringPath birth = createString("birth");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final EnumPath<com.umc.workbook.domain.enums.Gender> gender = createEnum("gender", com.umc.workbook.domain.enums.Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> inactiveDate = createDateTime("inactiveDate", java.time.LocalDateTime.class);

    public final BooleanPath inactiveStatus = createBoolean("inactiveStatus");

    public final BooleanPath isPhoneVerified = createBoolean("isPhoneVerified");

    public final StringPath loginId = createString("loginId");

    public final StringPath loginPassword = createString("loginPassword");

    public final StringPath memberAddress = createString("memberAddress");

    public final ListPath<com.umc.workbook.domain.mapping.MemberMission, com.umc.workbook.domain.mapping.QMemberMission> memberMissionList = this.<com.umc.workbook.domain.mapping.MemberMission, com.umc.workbook.domain.mapping.QMemberMission>createList("memberMissionList", com.umc.workbook.domain.mapping.MemberMission.class, com.umc.workbook.domain.mapping.QMemberMission.class, PathInits.DIRECT2);

    public final ListPath<com.umc.workbook.domain.mapping.MemberPretendFood, com.umc.workbook.domain.mapping.QMemberPretendFood> memberPretendFoodList = this.<com.umc.workbook.domain.mapping.MemberPretendFood, com.umc.workbook.domain.mapping.QMemberPretendFood>createList("memberPretendFoodList", com.umc.workbook.domain.mapping.MemberPretendFood.class, com.umc.workbook.domain.mapping.QMemberPretendFood.class, PathInits.DIRECT2);

    public final StringPath nickName = createString("nickName");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final StringPath profileImage = createString("profileImage");

    public final ListPath<Question, QQuestion> questionList = this.<Question, QQuestion>createList("questionList", Question.class, QQuestion.class, PathInits.DIRECT2);

    public final ListPath<Review, QReview> reviewList = this.<Review, QReview>createList("reviewList", Review.class, QReview.class, PathInits.DIRECT2);

    public final EnumPath<com.umc.workbook.domain.enums.Role> role = createEnum("role", com.umc.workbook.domain.enums.Role.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

