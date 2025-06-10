package net.serg.movie_matcher_command.controller;

import lombok.Data;

@Data
public class UpdateMovieCommand extends BaseCommand{
    private String title;
    private String genre;
    private String director;
    private int releaseYear;
}
