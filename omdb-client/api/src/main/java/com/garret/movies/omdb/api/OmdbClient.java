package com.garret.movies.omdb.api;

import com.garret.movies.omdb.entity.OmdbMovie;
import com.garret.movies.omdb.entity.ShortMovie;

import java.util.List;

public interface OmdbClient {

    List<ShortMovie> searchMovies(String title);

    OmdbMovie searchByImdbId(String imdbId);
}
