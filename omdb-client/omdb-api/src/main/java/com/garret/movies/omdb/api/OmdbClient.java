package com.garret.movies.omdb.api;

import com.garret.movies.omdb.dto.OmdbApiResponse;
import com.garret.movies.omdb.dto.OmdbApiSearchResponse;
import com.garret.movies.omdb.dto.OmdbMovie;
import com.garret.movies.omdb.dto.SimpleMovie;

import java.util.List;

public interface OmdbClient {

    OmdbApiSearchResponse<List<SimpleMovie>> searchMovies(String title, int page);

    OmdbApiResponse<OmdbMovie> searchByImdbId(String imdbId);
}
