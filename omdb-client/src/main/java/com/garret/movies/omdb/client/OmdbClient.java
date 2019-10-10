package com.garret.movies.omdb.client;

import com.garret.movies.dao.entity.Movie;

import java.util.List;

public interface OmdbClient {

    List<Movie> searchMovies(String title);

    Movie searchMovieByTitle(String title);
}
