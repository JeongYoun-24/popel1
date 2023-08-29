package com.project.bank.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLoanhistory is a Querydsl query type for Loanhistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLoanhistory extends EntityPathBase<Loanhistory> {

    private static final long serialVersionUID = 1569289385L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLoanhistory loanhistory = new QLoanhistory("loanhistory");

    public final QAccount account;

    public final DateTimePath<java.time.LocalDateTime> loanDate = createDateTime("loanDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> loanNo = createNumber("loanNo", Long.class);

    public final QLoanProduct loanProduct;

    public final DateTimePath<java.time.LocalDateTime> redemptionDate = createDateTime("redemptionDate", java.time.LocalDateTime.class);

    public QLoanhistory(String variable) {
        this(Loanhistory.class, forVariable(variable), INITS);
    }

    public QLoanhistory(Path<? extends Loanhistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLoanhistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLoanhistory(PathMetadata metadata, PathInits inits) {
        this(Loanhistory.class, metadata, inits);
    }

    public QLoanhistory(Class<? extends Loanhistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.account = inits.isInitialized("account") ? new QAccount(forProperty("account"), inits.get("account")) : null;
        this.loanProduct = inits.isInitialized("loanProduct") ? new QLoanProduct(forProperty("loanProduct"), inits.get("loanProduct")) : null;
    }

}

