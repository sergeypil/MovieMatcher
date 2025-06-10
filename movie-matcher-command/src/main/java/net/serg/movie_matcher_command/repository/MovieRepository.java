package net.serg.movie_matcher_command.repository;

import net.serg.movie_matcher_command.entity.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<EventModel, String> {
    List<EventModel> findByAggregateIdentifier(String aggregateIdentifier);
}