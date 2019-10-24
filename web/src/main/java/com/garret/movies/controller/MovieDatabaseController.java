package com.garret.movies.controller;

import com.garret.movies.dao.entity.Movie;
import com.garret.movies.service.api.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/database-movies")
public class MovieDatabaseController {

    private final MovieService movieService;

    @DeleteMapping
    public void deleteAll() {
        movieService.deleteAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getById(@PathVariable("id") Long id) {
        return movieService.getById(id)
                .map(movie -> new ResponseEntity<>(movie, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable("id") Long id) {
        boolean isDeleted = movieService.deleteById(id);
        if (isDeleted) {
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public List<Movie> getAllByParams(@RequestParam(required = false) Map<String, String> params) {
        return movieService.getAllByParams(params);
    }
}
