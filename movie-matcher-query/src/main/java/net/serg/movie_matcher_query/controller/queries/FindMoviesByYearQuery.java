package net.serg.movie_matcher_query.controller.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindMoviesByYearQuery extends BaseQuery {
    private int year;
}