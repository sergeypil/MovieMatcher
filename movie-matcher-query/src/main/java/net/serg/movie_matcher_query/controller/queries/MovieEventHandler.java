package net.serg.movie_matcher_query.controller.queries;

import lombok.RequiredArgsConstructor;
import net.serg.movie_matcher_query.entity.MovieEntity;
import net.serg.movie_matcher_query.event.MovieAddedEvent;
import net.serg.movie_matcher_query.event.MovieDeletedEvent;
import net.serg.movie_matcher_query.event.MovieUpdatedEvent;
import net.serg.movie_matcher_query.repository.MovieRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieEventHandler implements EventHandler {
    
    private final MovieRepository movieRepository;

    @Override
    public void on(MovieAddedEvent event) {
        var movie = MovieEntity
            .builder()
            .id(event.getId())
            .title(event.getTitle())
            .genre(event.getGenre())
            .releaseYear(event.getReleaseYear())
            .director(event.getDirector())
            .build();
        movieRepository.save(movie);
    }

    @Override
    public void on(MovieUpdatedEvent event) {
        var existingMovie = movieRepository.findById(event.getId());
        if (existingMovie != null) {
            existingMovie.setTitle(event.getTitle());
            existingMovie.setGenre(event.getGenre());
            existingMovie.setReleaseYear(event.getReleaseYear());
            existingMovie.setDirector(event.getDirector());
            movieRepository.save(existingMovie);
        }
    }

    @Override
    public void on(MovieDeletedEvent event) {
        movieRepository.deleteById(event.getId());
    }
}