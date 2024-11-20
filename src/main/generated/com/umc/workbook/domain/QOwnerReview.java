package com.umc.workbook.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOwnerReview is a Querydsl query type for OwnerReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOwnerReview extends EntityPathBase<OwnerReview> {

    private static final long serialVersionUID = -1268909179L;

    public static final QOwnerReview ownerReview = new QOwnerReview("ownerReview");

    public final com.umc.workbook.domain.commons.QBaseEntity _super = new com.umc.workbook.domain.commons.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath ownerReviewContent = createString("ownerReviewContent");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QOwnerReview(String variable) {
        super(OwnerReview.class, forVariable(variable));
    }

    public QOwnerReview(Path<? extends OwnerReview> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOwnerReview(PathMetadata metadata) {
        super(OwnerReview.class, metadata);
    }

}

