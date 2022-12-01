package com.academy.jdbc.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLike_Pk is a Querydsl query type for Pk
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QLike_Pk extends BeanPath<Like.Pk> {

    private static final long serialVersionUID = -1959566581L;

    public static final QLike_Pk pk = new QLike_Pk("pk");

    public final NumberPath<Integer> postId = createNumber("postId", Integer.class);

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public QLike_Pk(String variable) {
        super(Like.Pk.class, forVariable(variable));
    }

    public QLike_Pk(Path<? extends Like.Pk> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLike_Pk(PathMetadata metadata) {
        super(Like.Pk.class, metadata);
    }

}

