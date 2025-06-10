package net.serg.movie_matcher_command.service;

import net.serg.movie_matcher_command.event.MovieAddedEvent;
import net.serg.movie_matcher_command.event.MovieDeletedEvent;
import net.serg.movie_matcher_command.event.MovieUpdatedEvent;

public interface EventHandler {
    void on(MovieAddedEvent event);
    void on(MovieUpdatedEvent event);
    void on(MovieDeletedEvent event);
}