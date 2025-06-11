package net.serg.movie_matcher_query.service;

import net.serg.movie_matcher_query.controller.queries.BaseQuery;
import net.serg.movie_matcher_query.entity.BaseEntity;

import java.util.List;

public interface QueryDispatcher {
    <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler);
    <U extends BaseEntity> List<U> send(BaseQuery query);
}


