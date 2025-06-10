package net.serg.movie_matcher_command.service;

public interface EventSourcingHandler<T> {
    void save(AggregateRoot aggregate);
    T getById(String id);
    void republishEvents();
}