package net.serg.movie_matcher_query.controller;

import net.serg.movie_matcher_query.entity.MovieEntity;
import net.serg.movie_matcher_query.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/util")
public class UtilController {

    @Autowired
    private MovieRepository movieRepository;
    
    @PostMapping("/movies")
    public ResponseEntity<String> saveMovie(@RequestBody MovieEntity movie) {
        try {
            movieRepository.save(movie);
            return new ResponseEntity<>("Movie saved successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to save movie: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/movies")
    public ResponseEntity<List<MovieEntity>> getAllMovies() {
        try {
            List<MovieEntity> movies = movieRepository.findAll();
            if (movies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(movies, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/movies/{id}")
    public ResponseEntity<MovieEntity> getMovieById(@PathVariable String id) {
        try {
            MovieEntity movie = movieRepository.findById(id);
            if (movie == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/movies/{id}")
    public ResponseEntity<String> deleteMovieById(@PathVariable String id) {
        try {
            movieRepository.deleteById(id);
            return new ResponseEntity<>("Movie deleted successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete movie: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}