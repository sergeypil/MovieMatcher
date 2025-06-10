package net.serg.movie_matcher_command.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.serg.movie_matcher_command.event.BaseEvent;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@Getter
@Setter
@Slf4j
public abstract class AggregateRoot {

    protected String id;
    private int version = -1;
    private final List<BaseEvent> changes = new ArrayList<>();

    public List<BaseEvent> getUncommittedChanges() {
        return this.changes;
    }

    public void markChangesAsCommitted() {
        this.changes.clear();
    }

    protected void applyChange(BaseEvent event, Boolean isNewEvent) {
        try {
            var method = getClass().getDeclaredMethod("apply", event.getClass());
            method.setAccessible(true);
            method.invoke(this, event);
        } catch (NoSuchMethodException e) {
            log.error("No");
        } catch (Exception e) {
            log.error("Error applying event to aggregate: {}", e.getMessage(), e);
            log.warn(MessageFormat.format("The apply method was not found in the aggregate for {0}",
                                          event
                                              .getClass()
                                              .getName()));
        } finally {
            if (isNewEvent) {
                changes.add(event); 
            }
        }
    }

    public void raiseEvent(BaseEvent event) {
        applyChange(event, true);
    }

    public void replayEvents(Iterable<BaseEvent> events) {
        events.forEach(event -> applyChange(event, false));
    }
}