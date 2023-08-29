package com.project.bank.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBank is a Querydsl query type for Bank
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBank extends EntityPathBase<Bank> {

    private static final long serialVersionUID = -1489473833L;

    public static final QBank bank = new QBank("bank");

    public final StringPath bankName = createString("bankName");

    public final NumberPath<Long> bankNo = createNumber("bankNo", Long.class);

    public QBank(String variable) {
        super(Bank.class, forVariable(variable));
    }

    public QBank(Path<? extends Bank> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBank(PathMetadata metadata) {
        super(Bank.class, metadata);
    }

}

