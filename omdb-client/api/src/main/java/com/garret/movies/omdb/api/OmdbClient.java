package com.garret.movies.omdb.api;

import com.garret.movies.omdb.dto.MoviesResponse;
import com.garret.movies.omdb.dto.OmdbMovie;

public interface OmdbClient<T> {

    MoviesResponse<T> searchMovies(String title, int page);

    OmdbMovie searchByImdbId(String imdbId);
}
