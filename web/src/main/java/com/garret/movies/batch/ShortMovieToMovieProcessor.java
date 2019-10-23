package com.garret.movies.batch;

import com.garret.movies.dao.entity.Movie;
import com.garret.movies.dao.entity.ShortMovie;
import com.garret.movies.omdb.api.OmdbClient;
import com.garret.movies.service.api.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShortMovieToMovieProcessor implements ItemProcessor<ShortMovie, Movie> {

    private final OmdbClient omdbClient;
    private final MovieService movieService;

    @Override
    public Movie process(ShortMovie shortMovie) throws Exception {
        if (movieService.existsByImdbId(shortMovie.getImdbId())) {
            return null;
        }
        return omdbClient.searchByImdbId(shortMovie.getImdbId());
    }
}
