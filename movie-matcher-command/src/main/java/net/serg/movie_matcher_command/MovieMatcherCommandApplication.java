package net.serg.movie_matcher_command;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.serg.movie_matcher_command.controller.commands.AddMovieCommand;
import net.serg.movie_matcher_command.controller.commands.DeleteMovieCommand;
import net.serg.movie_matcher_command.controller.commands.UpdateMovieCommand;
import net.serg.movie_matcher_command.service.CommandDispatcher;
import net.serg.movie_matcher_command.controller.commands.CommandHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class MovieMatcherCommandApplication {
	
	private final CommandDispatcher commandDispatcher;
	
	private final CommandHandler commandHandler;

	public static void main(String[] args) {
		SpringApplication.run(MovieMatcherCommandApplication.class, args);
	}

	@PostConstruct
	public void registerHandlers() {
		commandDispatcher.registerHandler(AddMovieCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(UpdateMovieCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(DeleteMovieCommand.class, commandHandler::handle);
	}
}
