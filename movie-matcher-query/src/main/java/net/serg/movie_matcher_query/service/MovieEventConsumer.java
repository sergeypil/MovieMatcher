package net.serg.movie_matcher_query.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import net.serg.movie_matcher_query.event.BaseEvent;
import net.serg.movie_matcher_query.event.MovieAddedEvent;
import net.serg.movie_matcher_query.event.MovieDeletedEvent;
import net.serg.movie_matcher_query.event.MovieUpdatedEvent;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MovieEventConsumer {

    private final MovieEventHandler eventHandler;

    @JmsListener(destination = "MovieEventsQueue")
    public void consumeMovieEvents(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        BaseEvent event = objectMapper.readValue(message, BaseEvent.class);
        if (event instanceof MovieAddedEvent) {
            eventHandler.on((MovieAddedEvent) event);
        } else if (event instanceof MovieUpdatedEvent) {
            eventHandler.on((MovieUpdatedEvent) event);
        } else if (event instanceof MovieDeletedEvent) {
            eventHandler.on((MovieDeletedEvent) event);
        } else {
            throw new IllegalArgumentException("Unknown event type: " + event.getClass().getName());
        }
    }
}