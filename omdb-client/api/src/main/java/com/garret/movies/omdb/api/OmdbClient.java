package com.garret.movies.omdb.api;

import com.garret.movies.dao.entity.Movie;

import java.util.List;

public interface OmdbClient {
    List<Movie> searchMovies(String title);
}
