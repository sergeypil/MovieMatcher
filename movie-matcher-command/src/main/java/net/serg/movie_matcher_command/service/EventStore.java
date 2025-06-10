package net.serg.movie_matcher_command.service;

import net.serg.movie_matcher_command.event.BaseEvent;

import java.util.List;

public interface EventStore {
    void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion);
    List<BaseEvent> getEvents(String aggregateId); 
    List<String> getAggregateIds(); 
}