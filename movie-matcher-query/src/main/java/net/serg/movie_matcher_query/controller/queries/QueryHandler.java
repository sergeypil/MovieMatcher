package net.serg.movie_matcher_query.controller.queries;

import net.serg.movie_matcher_query.entity.BaseEntity;

import java.util.List;

public interface QueryHandler {
    List<BaseEntity> handle(FindAllMoviesQuery query);
    List<BaseEntity> handle(FindMovieByIdQuery query);
    List<BaseEntity> handle(FindMoviesByGenreQuery query);
    List<BaseEntity> handle(FindMoviesByYearQuery query);
}
