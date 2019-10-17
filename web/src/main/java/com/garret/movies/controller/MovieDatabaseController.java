package com.garret.movies.controller;

import com.garret.movies.dao.entity.Movie;
import com.garret.movies.service.api.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/db")
public class MovieDatabaseController {

    private MovieService movieService;

    @GetMapping("/genre/{genre}")
    public List<Movie> getByGenre(@PathVariable("genre") String genre) {
        return movieService.getAllByGenre(genre);
    }

    @GetMapping("/lang/{language}")
    public List<Movie> getByLanguage(@PathVariable("language") String language) {
        return movieService.getAllByLanguage(language);
    }

    @GetMapping("/country/{country}")
    public List<Movie> getByCountry(@PathVariable("country") String country) {
        return movieService.getAllByCountry(country);
    }

    @GetMapping("/actor/{actor}")
    public List<Movie> getByActor(@PathVariable("actor") String actor) {
        return movieService.getAllByActor(actor);
    }

    @GetMapping("/year/{year}")
    public List<Movie> getByYear(@PathVariable("year") Integer year) {
        return movieService.getAllByYear(year);
    }

    @GetMapping("/top/votes")
    public List<Movie> getTopByVotes() {
        return movieService.getTopByVotes();
    }

    @GetMapping("/top/rating")
    public List<Movie> getTopByRating() {
        return movieService.getTopByRating();
    }

    @GetMapping("/all")
    public List<Movie> getAllMovies() {
        return movieService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        movieService.deleteById(id);
    }
}
