package net.serg.movie_matcher_command.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.serg.movie_matcher_command.event.BaseEvent;

import java.util.Date;

@Entity
@Table(name = "eventStore")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Date timeStamp;
    private String aggregateIdentifier;
    private String aggregateType;
    private int eventVersion;
    private String eventType;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = BaseEventConverter.class)
    private BaseEvent eventData;
}

