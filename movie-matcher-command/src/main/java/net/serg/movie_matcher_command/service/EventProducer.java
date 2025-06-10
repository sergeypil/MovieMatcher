package net.serg.movie_matcher_command.service;

import net.serg.movie_matcher_command.event.BaseEvent;

public interface EventProducer {

    void produce(BaseEvent event);
}
