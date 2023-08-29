package com.project.bank.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHistory is a Querydsl query type for History
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHistory extends EntityPathBase<History> {

    private static final long serialVersionUID = -343739879L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHistory history = new QHistory("history");

    public final QAccount account;

    public final NumberPath<Integer> balance = createNumber("balance", Integer.class);

    public final StringPath chk = createString("chk");

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    public final StringPath memberName = createString("memberName");

    public final DateTimePath<java.time.LocalDateTime> updateDate = createDateTime("updateDate", java.time.LocalDateTime.class);

    public QHistory(String variable) {
        this(History.class, forVariable(variable), INITS);
    }

    public QHistory(Path<? extends History> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHistory(PathMetadata metadata, PathInits inits) {
        this(History.class, metadata, inits);
    }

    public QHistory(Class<? extends History> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.account = inits.isInitialized("account") ? new QAccount(forProperty("account"), inits.get("account")) : null;
    }

}

