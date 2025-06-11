package net.serg.movie_matcher_query.controller;

import net.serg.movie_matcher_query.controller.queries.FindAllMoviesQuery;
import net.serg.movie_matcher_query.controller.queries.FindMovieByIdQuery;
import net.serg.movie_matcher_query.controller.queries.FindMoviesByGenreQuery;
import net.serg.movie_matcher_query.controller.queries.FindMoviesByYearQuery;
import net.serg.movie_matcher_query.entity.MovieEntity;
import net.serg.movie_matcher_query.service.QueryDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/movieLookup")
public class MovieLookupController {

    @Autowired
    private QueryDispatcher queryDispatcher;

    @GetMapping("/")
    public ResponseEntity<MovieLookupResponse> getAllMovies() {
        try {
            List<MovieEntity> movies = queryDispatcher.send(new FindAllMoviesQuery());
            if (movies == null || movies.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(new MovieLookupResponse("Movies retrieved successfully!", movies), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new MovieLookupResponse("Failed to retrieve movies!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<MovieLookupResponse> getMovieById(@PathVariable String id) {
        try {
            List<MovieEntity> movies = queryDispatcher.send(new FindMovieByIdQuery(id));
            if (movies == null || movies.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(new MovieLookupResponse("Movie retrieved successfully!", movies), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new MovieLookupResponse("Failed to retrieve movie by ID!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byGenre/{genre}")
    public ResponseEntity<MovieLookupResponse> getMoviesByGenre(@PathVariable String genre) {
        try {
            List<MovieEntity> movies = queryDispatcher.send(new FindMoviesByGenreQuery(genre));
            if (movies == null || movies.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(new MovieLookupResponse("Movies retrieved successfully!", movies), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new MovieLookupResponse("Failed to retrieve movies by genre!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byYear/{year}")
    public ResponseEntity<MovieLookupResponse> getMoviesByYear(@PathVariable int year) {
        try {
            List<MovieEntity> movies = queryDispatcher.send(new FindMoviesByYearQuery(year));
            if (movies == null || movies.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(new MovieLookupResponse("Movies retrieved successfully!", movies), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new MovieLookupResponse("Failed to retrieve movies by year!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}