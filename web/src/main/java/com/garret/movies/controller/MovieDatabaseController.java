package com.garret.movies.controller;

import com.garret.movies.dao.entity.Movie;
import com.garret.movies.service.api.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public List<Movie> getAllByParams(@RequestParam MultiValueMap<String, String> params) {
        List<Movie> response = new ArrayList<>(movieService.getAll());
        if (params.containsKey("genre")) {
            response.retainAll(movieService.getAllByGenre(params.getFirst("genre")));
        }
        if (params.containsKey("language")) {
            response.retainAll(movieService.getAllByLanguage(params.getFirst("language")));
        }
        if (params.containsKey("actor")) {
            response.retainAll(movieService.getAllByActor(params.getFirst("actor")));
        }
        if (params.containsKey("year")) {
            response.retainAll(movieService.getAllByYear(Integer.parseInt(Objects.requireNonNull(params.getFirst("year")))));
        }
        if (params.containsKey("country")) {
            response.retainAll(movieService.getAllByCountry(params.getFirst("country")));
        }
        return response;
    }

    @GetMapping("/top-votes")
    public List<Movie> getTopByVotes() {
        return movieService.getTopByVotes();
    }

    @GetMapping("/top-rating")
    public List<Movie> getTopByRating() {
        return movieService.getTopByRating();
    }
}
