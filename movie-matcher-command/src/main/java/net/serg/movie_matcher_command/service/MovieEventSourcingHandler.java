package net.serg.movie_matcher_command.service;

import lombok.RequiredArgsConstructor;
import net.serg.movie_matcher_command.event.BaseEvent;
import net.serg.movie_matcher_command.event.MovieAggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class MovieEventSourcingHandler implements EventSourcingHandler<MovieAggregate> {
    private final EventStore eventStore;
    
    private final EventProducer eventProducer;

    @Override
    public void save(AggregateRoot aggregate) {
        eventStore.saveEvents(aggregate.getId(), aggregate.getUncommittedChanges(), aggregate.getVersion());
        aggregate.markChangesAsCommitted();
    }

    @Override
    public MovieAggregate getById(String id) {
        var aggregate = new MovieAggregate();
        var events = eventStore.getEvents(id);
        if (events != null && !events.isEmpty()) {
            aggregate.replayEvents(events);
            var latestVersion = events.stream().map(BaseEvent::getVersion).max(Comparator.naturalOrder());
            aggregate.setVersion(latestVersion.orElse(-1));
        }
        return aggregate;
    }

    @Override
    public void republishEvents() {
        var aggregateIds = eventStore.getAggregateIds();
        for (var aggregateId : aggregateIds) {
            var events = eventStore.getEvents(aggregateId);
            for (var event : events) {
                eventProducer.produce(event);
            }
        }
    }
}