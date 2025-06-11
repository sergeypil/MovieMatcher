package net.serg.movie_matcher_query.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class MovieDeletedEvent extends BaseEvent{
}
