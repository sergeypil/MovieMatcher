package net.serg.movie_matcher_command.controller.commands;

public interface CommandHandler {
    void handle(AddMovieCommand command);

    void handle(UpdateMovieCommand command);

    void handle(DeleteMovieCommand command);
}
