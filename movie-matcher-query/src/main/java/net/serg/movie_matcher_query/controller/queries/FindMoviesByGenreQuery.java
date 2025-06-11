package net.serg.movie_matcher_query.controller.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindMoviesByGenreQuery extends BaseQuery {
    private String genre;
}