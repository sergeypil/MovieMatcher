package net.serg.movie_matcher_query.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class MovieUpdatedEvent extends BaseEvent {
    private String title;
    private String genre;
    private String director;
    private int releaseYear;
}
