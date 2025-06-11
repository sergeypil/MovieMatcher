package net.serg.movie_matcher_query.controller.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindMovieByIdQuery extends BaseQuery {
    private String id;
}