package net.serg.movie_matcher_command.controller.commands;

import lombok.Data;

@Data
public class AddMovieCommand extends BaseCommand {
    private String title;
    private String genre;
    private String director;
    private int releaseYear;
}
