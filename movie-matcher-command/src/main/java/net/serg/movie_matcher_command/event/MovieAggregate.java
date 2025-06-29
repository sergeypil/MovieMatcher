package net.serg.movie_matcher_command.event;

import lombok.NoArgsConstructor;
import net.serg.movie_matcher_command.controller.commands.AddMovieCommand;
import net.serg.movie_matcher_command.controller.commands.DeleteMovieCommand;
import net.serg.movie_matcher_command.controller.commands.UpdateMovieCommand;
import net.serg.movie_matcher_command.service.AggregateRoot;

@NoArgsConstructor
public class MovieAggregate extends AggregateRoot {
    private String title;
    private String genre;
    private int releaseYear;
    private String director;

    public MovieAggregate(AddMovieCommand command) {
        raiseEvent(MovieAddedEvent.builder()
            .id(command.getId())
            .title(command.getTitle())
            .genre(command.getGenre())
            .director(command.getDirector())
            .releaseYear(command.getReleaseYear())
            .build());
    }

    public void apply(MovieAddedEvent event) {
        this.id = event.getId();
        this.title = event.getTitle();
        this.genre = event.getGenre();
        this.releaseYear = event.getReleaseYear();
        this.director = event.getDirector();
    }

    public void updateMovie(UpdateMovieCommand command) {
        raiseEvent(MovieUpdatedEvent
                       .builder()
                       .id(command.getId())
                       .title(command.getTitle())
                       .genre(command.getGenre())
                       .director(command.getDirector())
                       .releaseYear(command.getReleaseYear())
                       .build(
                       ));
    }

    public void apply(MovieUpdatedEvent event) {
        this.title = event.getTitle();
        this.genre = event.getGenre();
        this.releaseYear = event.getReleaseYear();
        this.director = event.getDirector();
    }

    public void deleteMovie(DeleteMovieCommand command) {
        raiseEvent(MovieDeletedEvent.builder().id(command.getId()).build());
    }

    public void apply(MovieDeletedEvent event) {
        this.id = event.getId();
    }
}