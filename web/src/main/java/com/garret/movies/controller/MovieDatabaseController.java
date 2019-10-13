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

    @GetMapping("/actor/{lastName}")
    public List<Movie> getByActorLastName(@PathVariable("lastName") String lastName) {
        return movieService.getAllByActorsLastName(lastName);
    }

    @GetMapping("/year/{year}")
    public List<Movie> getByYear(@PathVariable("year") Integer year) {
        String start = year + "-01-01";
        String end = year + "-12-31";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = formatter.parse(start);
            Date endDate = formatter.parse(end);
            return movieService.getAllByYear(startDate, endDate);
        } catch (ParseException e) {
            throw new RuntimeException("Can't parse date from DB");
        }
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
