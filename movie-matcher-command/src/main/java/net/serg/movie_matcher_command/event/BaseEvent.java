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
    use = JsonTypeInfo.Id.NAME, // Используем имя типа для идентификации подклассов
    include = JsonTypeInfo.As.PROPERTY, // Тип будет храниться как свойство JSON
    property = "type" // Имя свойства, которое указывает тип
    )
@JsonSubTypes({
    @JsonSubTypes.Type(value = MovieAddedEvent.class, name = "MovieAddedEvent"),
    @JsonSubTypes.Type(value = MovieUpdatedEvent.class, name = "MovieUpdatedEvent")
})
public abstract class BaseEvent extends Message {
    private int version;
}
