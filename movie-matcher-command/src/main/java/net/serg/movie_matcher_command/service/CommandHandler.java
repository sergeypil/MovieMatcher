package net.serg.movie_matcher_command.service;

import net.serg.movie_matcher_command.controller.AddMovieCommand;
import net.serg.movie_matcher_command.controller.DeleteMovieCommand;
import net.serg.movie_matcher_command.controller.UpdateMovieCommand;

public interface CommandHandler {
    void handle(AddMovieCommand command);

    void handle(UpdateMovieCommand command);

    void handle(DeleteMovieCommand command);
}
