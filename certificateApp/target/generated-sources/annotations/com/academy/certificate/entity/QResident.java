package com.academy.certificate.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QResident is a Querydsl query type for Resident
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResident extends EntityPathBase<Resident> {

    private static final long serialVersionUID = 1421692629L;

    public static final QResident resident = new QResident("resident");

    public final DateTimePath<java.time.LocalDateTime> birthDate = createDateTime("birthDate", java.time.LocalDateTime.class);

    public final EnumPath<com.academy.certificate.enums.BirthPlaceCode> birthPlaceCode = createEnum("birthPlaceCode", com.academy.certificate.enums.BirthPlaceCode.class);

    public final DateTimePath<java.time.LocalDateTime> deathDate = createDateTime("deathDate", java.time.LocalDateTime.class);

    public final StringPath deathPlaceAddress = createString("deathPlaceAddress");

    public final EnumPath<com.academy.certificate.enums.DeathPlaceCode> deathPlaceCode = createEnum("deathPlaceCode", com.academy.certificate.enums.DeathPlaceCode.class);

    public final EnumPath<com.academy.certificate.enums.GenderCode> genderCode = createEnum("genderCode", com.academy.certificate.enums.GenderCode.class);

    public final StringPath name = createString("name");

    public final StringPath registrationBaseAddress = createString("registrationBaseAddress");

    public final StringPath residentRegistrationNumber = createString("residentRegistrationNumber");

    public final NumberPath<Long> residentSerialNumber = createNumber("residentSerialNumber", Long.class);

    public QResident(String variable) {
        super(Resident.class, forVariable(variable));
    }

    public QResident(Path<? extends Resident> path) {
        super(path.getType(), path.getMetadata());
    }

    public QResident(PathMetadata metadata) {
        super(Resident.class, metadata);
    }

}

