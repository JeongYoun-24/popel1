package com.project.bank.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLoanProduct is a Querydsl query type for LoanProduct
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLoanProduct extends EntityPathBase<LoanProduct> {

    private static final long serialVersionUID = 1997534436L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLoanProduct loanProduct = new QLoanProduct("loanProduct");

    public final QBank bank;

    public final StringPath interest = createString("interest");

    public final StringPath loanMoney = createString("loanMoney");

    public final StringPath productName = createString("productName");

    public final NumberPath<Long> productNo = createNumber("productNo", Long.class);

    public final StringPath repaymentDate = createString("repaymentDate");

    public QLoanProduct(String variable) {
        this(LoanProduct.class, forVariable(variable), INITS);
    }

    public QLoanProduct(Path<? extends LoanProduct> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLoanProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLoanProduct(PathMetadata metadata, PathInits inits) {
        this(LoanProduct.class, metadata, inits);
    }

    public QLoanProduct(Class<? extends LoanProduct> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bank = inits.isInitialized("bank") ? new QBank(forProperty("bank")) : null;
    }

}

