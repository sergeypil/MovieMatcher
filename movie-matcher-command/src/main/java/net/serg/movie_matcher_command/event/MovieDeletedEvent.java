package net.serg.movie_matcher_command.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class MovieDeletedEvent extends BaseEvent{
}
