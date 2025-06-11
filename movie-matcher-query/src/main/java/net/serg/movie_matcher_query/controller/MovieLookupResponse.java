package net.serg.movie_matcher_query.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.serg.movie_matcher_query.entity.MovieEntity;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieLookupResponse {
    private String message;
    private List<MovieEntity> movies;

    public MovieLookupResponse(String message) {
        this.message = message;
    }
}