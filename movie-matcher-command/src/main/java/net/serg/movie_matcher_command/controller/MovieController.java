package net.serg.movie_matcher_command.controller;

import lombok.RequiredArgsConstructor;
import net.serg.movie_matcher_command.controller.commands.AddMovieCommand;
import net.serg.movie_matcher_command.controller.commands.DeleteMovieCommand;
import net.serg.movie_matcher_command.controller.commands.UpdateMovieCommand;
import net.serg.movie_matcher_command.controller.dto.AddMovieResponse;
import net.serg.movie_matcher_command.service.CommandDispatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieController {
    
    private final CommandDispatcher commandDispatcher;

    @PostMapping
    public ResponseEntity<AddMovieResponse> addMovie(@RequestBody AddMovieCommand command) {
        var id = UUID.randomUUID().toString();
        command.setId(id);
        commandDispatcher.send(command);
        return new ResponseEntity<>(new AddMovieResponse("Movie added successfully", id), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateMovie(@RequestBody UpdateMovieCommand command) {
        commandDispatcher.send(command);
        return new ResponseEntity<>("Movie updated successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable String id) {
        commandDispatcher.send(new DeleteMovieCommand(id));
        return new ResponseEntity<>("Movie deleted successfully!", HttpStatus.OK);
    }
}