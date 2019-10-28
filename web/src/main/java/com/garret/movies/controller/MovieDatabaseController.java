package com.garret.movies.controller;

import com.garret.movies.service.api.MovieService;
import com.garret.movies.service.dto.MovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieDatabaseController {

    private final MovieService movieService;

    @GetMapping("/{id}")
    public MovieDto getById(@PathVariable("id") Long id) {
        return movieService.getById(id).orElse(new MovieDto());
    }

    @GetMapping
    public List<MovieDto> findAll() {
        return movieService.getAll();
    }

    @DeleteMapping
    public void deleteAll() {
        movieService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        movieService.deleteById(id);
    }

    /*@GetMapping("/search")TODO jooq
    public List<Movie> getAllByParams(@RequestParam(required = false) MultiValueMap<String, String> params) {

    }*/
}
