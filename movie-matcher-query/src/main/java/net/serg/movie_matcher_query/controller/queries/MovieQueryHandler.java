package net.serg.movie_matcher_query.controller.queries;

import lombok.RequiredArgsConstructor;
import net.serg.movie_matcher_query.entity.BaseEntity;
import net.serg.movie_matcher_query.entity.MovieEntity;
import net.serg.movie_matcher_query.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieQueryHandler implements QueryHandler {
    
    private final MovieRepository movieRepository;
    
    @Override
    public List<BaseEntity> handle(FindAllMoviesQuery query) {
        Iterable<MovieEntity> movies = movieRepository.findAll();
        List<BaseEntity> movieList = new ArrayList<>();
        movies.forEach(movieList::add);
        return movieList.isEmpty() ? null : movieList; 
    }
    
    @Override
    public List<BaseEntity> handle(FindMovieByIdQuery query) {
        var movie = movieRepository.findById(query.getId());
        if (movie == null) {
            return null;
        }
        List<BaseEntity> movieList = new ArrayList<>();
        movieList.add(movie);
        return movieList;
    }
    
    @Override
    public List<BaseEntity> handle(FindMoviesByGenreQuery query) {
        List<MovieEntity> movies = movieRepository.findByGenre(query.getGenre());
        return movies.isEmpty() ? null : new ArrayList<>(movies);
    }
    
    @Override
    public List<BaseEntity> handle(FindMoviesByYearQuery query) {
        List<MovieEntity> movies = movieRepository.findByReleaseYear(query.getYear());
        return movies.isEmpty() ? null : new ArrayList<>(movies);
    }
}