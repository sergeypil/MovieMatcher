package net.serg.movie_matcher_query.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

@Slf4j
@Component
public class MovieMatcherEventsQueueListenerErrorHandler implements ErrorHandler {

    @Override
    public void handleError(Throwable t) {
        log.error("Error occurred while processing message from Movie Matcher events queue: {}", t.getMessage(), t);
    }
}
