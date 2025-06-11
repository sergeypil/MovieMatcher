package net.serg.movie_matcher_query;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.serg.movie_matcher_query.controller.queries.FindAllMoviesQuery;
import net.serg.movie_matcher_query.controller.queries.FindMovieByIdQuery;
import net.serg.movie_matcher_query.controller.queries.FindMoviesByGenreQuery;
import net.serg.movie_matcher_query.controller.queries.FindMoviesByYearQuery;
import net.serg.movie_matcher_query.controller.queries.QueryHandler;
import net.serg.movie_matcher_query.service.QueryDispatcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class MovieMatcherQueryApplication {
	
	private final QueryDispatcher queryDispatcher;
	private final QueryHandler queryHandler;

	public static void main(String[] args) {
		SpringApplication.run(MovieMatcherQueryApplication.class, args);
	}

	@PostConstruct
	public void registerHandlers() {
		queryDispatcher.registerHandler(FindAllMoviesQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindMoviesByYearQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindMovieByIdQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindMoviesByGenreQuery.class, queryHandler::handle);
	}
}
