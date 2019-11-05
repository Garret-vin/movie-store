package com.garret.movies.omdb.api;

import com.garret.movies.omdb.dto.OmdbMovie;
import com.garret.movies.omdb.dto.ShortMovie;

import java.util.List;

public interface OmdbClient {

    List<ShortMovie> searchMovies(String title);

    OmdbMovie searchByImdbId(String imdbId);
}
