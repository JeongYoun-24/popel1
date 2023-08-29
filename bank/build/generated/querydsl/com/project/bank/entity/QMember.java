package com.project.bank.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -841664395L;

    public static final QMember member = new QMember("member1");

    public final StringPath address = createString("address");

    public final StringPath age = createString("age");

    public final StringPath email = createString("email");

    public final StringPath gender = createString("gender");

    public final StringPath level = createString("level");

    public final StringPath memberId = createString("memberId");

    public final StringPath name = createString("name");

    public final StringPath phone = createString("phone");

    public final StringPath pswd = createString("pswd");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

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

