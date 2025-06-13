package net.serg.movie_matcher_command.service;

import net.serg.movie_matcher_command.controller.commands.BaseCommand;

@FunctionalInterface
public interface CommandHandlerMethod<T extends BaseCommand> {
    void handle(T command);
}
