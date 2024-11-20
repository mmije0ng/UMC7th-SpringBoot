package com.umc.workbook.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAlarmSetting is a Querydsl query type for AlarmSetting
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAlarmSetting extends EntityPathBase<AlarmSetting> {

    private static final long serialVersionUID = 1392433765L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAlarmSetting alarmSetting = new QAlarmSetting("alarmSetting");

    public final com.umc.workbook.domain.commons.QBaseEntity _super = new com.umc.workbook.domain.commons.QBaseEntity(this);

    public final EnumPath<com.umc.workbook.domain.enums.AlarmType> alarmType = createEnum("alarmType", com.umc.workbook.domain.enums.AlarmType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isAlarmSet = createBoolean("isAlarmSet");

    public final QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QAlarmSetting(String variable) {
        this(AlarmSetting.class, forVariable(variable), INITS);
    }

    public QAlarmSetting(Path<? extends AlarmSetting> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAlarmSetting(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAlarmSetting(PathMetadata metadata, PathInits inits) {
        this(AlarmSetting.class, metadata, inits);
    }

    public QAlarmSetting(Class<? extends AlarmSetting> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

