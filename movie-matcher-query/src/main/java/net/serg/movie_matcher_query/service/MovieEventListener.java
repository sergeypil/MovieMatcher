package net.serg.movie_matcher_query.service;

import lombok.RequiredArgsConstructor;
import net.serg.movie_matcher_query.entity.MovieQuery;
import net.serg.movie_matcher_query.repository.MovieQueryRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieEventListener {
    private final MovieQueryRepository movieQueryRepository;
    
    @SqsListener("<your-sqs-queue-name>")
    public void handleMovieAddedEvent(String message) {
        MovieQuery movie = new MovieQuery();
        movie.setTitle(message); // Пример
        movieQueryRepository.save(movie);
    }
}