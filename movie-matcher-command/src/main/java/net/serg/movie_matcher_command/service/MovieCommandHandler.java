package net.serg.movie_matcher_command.service;

import net.serg.movie_matcher_command.controller.AddMovieCommand;
import net.serg.movie_matcher_command.controller.DeleteMovieCommand;
import net.serg.movie_matcher_command.controller.UpdateMovieCommand;
import net.serg.movie_matcher_command.event.MovieAggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieCommandHandler implements CommandHandler {
    @Autowired
    private EventSourcingHandler<MovieAggregate> eventSourcingHandler;

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