package net.serg.movie_matcher_query.controller;

import net.serg.movie_matcher_query.entity.MovieQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieQueryController {
    private final MovieQueryService movieQueryService;

    public MovieQueryController(MovieQueryService movieQueryService) {
        this.movieQueryService = movieQueryService;
    }

    @GetMapping
    public List<MovieQuery> getAllMovies() {
        return movieQueryService.getAllMovies();
    }
}