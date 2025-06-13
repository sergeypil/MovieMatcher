package net.serg.movie_matcher_command.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import net.serg.movie_matcher_command.event.BaseEvent;

@Converter
public class BaseEventConverter implements AttributeConverter<BaseEvent, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(BaseEvent attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting BaseEvent to JSON string",  e);
        }
    }

    @Override
    public BaseEvent convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, BaseEvent.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting JSON string to BaseEvent", e);
        }
    }
}