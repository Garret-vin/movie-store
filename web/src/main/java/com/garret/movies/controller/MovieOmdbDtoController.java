package com.garret.movies.controller;

import com.garret.movies.dao.entity.ShortMovie;
import com.garret.movies.omdb.api.OmdbClient;
import com.garret.movies.omdb.impl.client.OmdbClientDTOImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
@AllArgsConstructor
public class MovieOmdbDtoController {

    private OmdbClientDTOImpl omdbClient;

    @GetMapping("/search/{title}")
    public List<ShortMovie> getShortMovies(@PathVariable("title") String title) {
        return omdbClient.searchMovies(title);
    }
}
