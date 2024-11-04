package com.umc.workbook.domain.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberPretendFood is a Querydsl query type for MemberPretendFood
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberPretendFood extends EntityPathBase<MemberPretendFood> {

    private static final long serialVersionUID = -1342089592L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberPretendFood memberPretendFood = new QMemberPretendFood("memberPretendFood");

    public final com.umc.workbook.domain.commons.QBaseEntity _super = new com.umc.workbook.domain.commons.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final com.umc.workbook.domain.QFoodCategory foodCategory;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.umc.workbook.domain.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMemberPretendFood(String variable) {
        this(MemberPretendFood.class, forVariable(variable), INITS);
    }

    public QMemberPretendFood(Path<? extends MemberPretendFood> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberPretendFood(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberPretendFood(PathMetadata metadata, PathInits inits) {
        this(MemberPretendFood.class, metadata, inits);
    }

    public QMemberPretendFood(Class<? extends MemberPretendFood> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.foodCategory = inits.isInitialized("foodCategory") ? new com.umc.workbook.domain.QFoodCategory(forProperty("foodCategory")) : null;
        this.member = inits.isInitialized("member") ? new com.umc.workbook.domain.QMember(forProperty("member")) : null;
    }

}

