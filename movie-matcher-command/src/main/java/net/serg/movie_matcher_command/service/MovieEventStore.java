package net.serg.movie_matcher_command.service;

import lombok.RequiredArgsConstructor;
import net.serg.movie_matcher_command.entity.EventModel;
import net.serg.movie_matcher_command.event.BaseEvent;
import net.serg.movie_matcher_command.exception.AggregateNotFoundException;
import net.serg.movie_matcher_command.exception.ConcurrencyException;
import net.serg.movie_matcher_command.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieEventStore implements EventStore {

    private final MovieRepository eventStoreRepository;
    private final EventProducer eventProducer;

    @Override
    public void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
        List<EventModel> eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (expectedVersion != -1 && eventStream.get(eventStream.size() - 1).getEventVersion() != expectedVersion) {
            throw new ConcurrencyException();
        }
        var version = expectedVersion;
        for (var event : events) {
            version++;
            event.setVersion(version);
            var eventModel = EventModel.builder()
                                       .timeStamp(new Date())
                                       .aggregateIdentifier(aggregateId)
                                       .aggregateType("MovieAggregate")
                                       .eventVersion(version)
                                       .eventType(event.getClass().getTypeName())
                                       .eventData(event)
                                       .build();
            EventModel persistedEvent = eventStoreRepository.save(eventModel);
            if (!persistedEvent.getId().isEmpty()) {
                eventProducer.produce(event);
            }
        }
    }

    @Override
    public List<BaseEvent> getEvents(String aggregateId) {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (eventStream == null || eventStream.isEmpty()) {
            throw new AggregateNotFoundException("Aggregate not found for ID: " + aggregateId);
        }
        return eventStream.stream().map(EventModel::getEventData).collect(Collectors.toList());
    }

    @Override
    public List<String> getAggregateIds() {
        return eventStoreRepository.findAll().stream()
                                   .map(EventModel::getAggregateIdentifier)
                                   .distinct()
                                   .collect(Collectors.toList());
    }
}