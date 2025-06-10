package net.serg.movie_matcher_command.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.serg.movie_matcher_command.event.Message;

@Data
@NoArgsConstructor
public abstract class BaseCommand extends Message {
    public BaseCommand(String id) {
        super(id);
    }
}
