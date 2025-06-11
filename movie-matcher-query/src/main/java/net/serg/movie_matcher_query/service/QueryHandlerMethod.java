package net.serg.movie_matcher_query.service;

import net.serg.movie_matcher_query.controller.queries.BaseQuery;
import net.serg.movie_matcher_query.entity.BaseEntity;

import java.util.List;

@FunctionalInterface
public interface QueryHandlerMethod<T extends BaseQuery> {
    List<BaseEntity> handle(T query);
}
