package com.garret.movies.controller;

import com.garret.movies.dao.entity.Movie;
import com.garret.movies.omdb.api.OmdbClient;
import com.garret.movies.service.api.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class MovieOmdbController {

    private OmdbClient omdbClient;
    private MovieService movieService;

    @GetMapping("/search/{title}")
    public List<Movie> searchMovies(@PathVariable("title") String title) {
        List<Movie> movieList = omdbClient.searchMovies(title);
        List<Movie> newMovies = movieList.stream().filter(movieService::isNotPresent).collect(Collectors.toList());
        movieService.saveAll(newMovies);
        return movieList;
    }

    @GetMapping("/title/{title}")
    public Movie searchMovie(@PathVariable("title") String title) {
        return omdbClient.searchMovieByTitle(title);
    }
}
