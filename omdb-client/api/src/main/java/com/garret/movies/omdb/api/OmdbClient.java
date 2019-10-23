package com.garret.movies.omdb.api;

import com.garret.movies.dao.entity.Movie;
import com.garret.movies.dao.entity.ShortMovie;

import java.util.List;

public interface OmdbClient {

    List<ShortMovie> searchMovies(String title);

    Movie searchByImdbId(String imdbId);
}
