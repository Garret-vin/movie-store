package com.garret.movies.batch;

import com.garret.movies.omdb.api.OmdbClient;
import com.garret.movies.omdb.entity.OmdbMovie;
import com.garret.movies.omdb.entity.ShortMovie;
import com.garret.movies.service.api.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@StepScope
@RequiredArgsConstructor
public class ShortMovieToOmdbMovieProcessor implements ItemProcessor<ShortMovie, OmdbMovie> {

    private final OmdbClient omdbClient;
    private final MovieService movieService;

    @Override
    public OmdbMovie process(ShortMovie shortMovie) throws Exception {
        if (movieService.existsByImdbId(shortMovie.getImdbId())) {
            return null;
        }
        return omdbClient.searchByImdbId(shortMovie.getImdbId());
    }
}
