package net.serg.movie_matcher_command.controller;

import lombok.RequiredArgsConstructor;
import net.serg.movie_matcher_command.service.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieController {
    @Autowired
    private final CommandDispatcher commandDispatcher;

    @PostMapping
    public ResponseEntity<String> addMovie(@RequestBody AddMovieCommand command) {
        commandDispatcher.send(command);
        return new ResponseEntity<>("Movie added successfully!", HttpStatus.CREATED);
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