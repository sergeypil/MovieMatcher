package net.serg.movie_matcher_command.controller.commands;

import lombok.RequiredArgsConstructor;
import net.serg.movie_matcher_command.event.MovieAggregate;
import net.serg.movie_matcher_command.service.EventSourcingHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieCommandHandler implements CommandHandler {
    
    private final EventSourcingHandler<MovieAggregate> eventSourcingHandler;

    public void handle(AddMovieCommand command) {
        var aggregate = new MovieAggregate(command);
        eventSourcingHandler.save(aggregate);
    }

    public void handle(UpdateMovieCommand command) {
        var aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.updateMovie(command);
        eventSourcingHandler.save(aggregate);
    }

    public void handle(DeleteMovieCommand command) {
        var aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.deleteMovie(command);
        eventSourcingHandler.save(aggregate);
    }
}