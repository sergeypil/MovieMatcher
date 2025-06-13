package net.serg.movie_matcher_command.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "type" 
    )
@JsonSubTypes({
    @JsonSubTypes.Type(value = MovieAddedEvent.class, name = "MovieAddedEvent"),
    @JsonSubTypes.Type(value = MovieUpdatedEvent.class, name = "MovieUpdatedEvent"),
    @JsonSubTypes.Type(value = MovieDeletedEvent.class, name = "MovieDeletedEvent")
})
public abstract class BaseEvent extends Message {
    private int version;
}
