package com.garret.movies.controller;

import com.garret.movies.service.api.MovieService;
import com.garret.movies.service.dto.response.MovieDto;
import com.garret.movies.service.dto.response.SimpleMoviesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieDatabaseController {

    private final MovieService movieService;

    @GetMapping("/{id}")
    public MovieDto getById(@PathVariable("id") Long id) {
        return movieService.getById(id).orElse(null);
    }

    @GetMapping
    public SimpleMoviesResponse findAll(
            @PageableDefault(sort = "imdbRating", direction = Sort.Direction.DESC) Pageable pageable) {
        return movieService.getAll(pageable);
    }

    @DeleteMapping
    public void deleteAll() {
        movieService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        movieService.deleteById(id);
    }

}
