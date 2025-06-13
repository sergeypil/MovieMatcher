package net.serg.movie_matcher_query.controller.queries;

import net.serg.movie_matcher_query.event.MovieAddedEvent;
import net.serg.movie_matcher_query.event.MovieDeletedEvent;
import net.serg.movie_matcher_query.event.MovieUpdatedEvent;

public interface EventHandler {
    void on(MovieAddedEvent event);
    void on(MovieUpdatedEvent event);
    void on(MovieDeletedEvent event);
}