package com.garret.movies.batch;

import com.garret.movies.omdb.api.OmdbClient;
import com.garret.movies.omdb.dto.OmdbApiResponse;
import com.garret.movies.omdb.dto.OmdbMovie;
import com.garret.movies.omdb.dto.SimpleMovie;
import com.garret.movies.service.api.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@StepScope
@RequiredArgsConstructor
public class SimpleMovieToOmdbMovieProcessor implements ItemProcessor<SimpleMovie, OmdbMovie> {

    private final OmdbClient omdbClient;
    private final MovieService movieService;

    @Override
    public OmdbMovie process(SimpleMovie simpleMovie) throws Exception {
        if (movieService.existsByImdbId(simpleMovie.getImdbId())) {
            return null;
        }
        OmdbApiResponse<OmdbMovie> response = omdbClient.searchByImdbId(simpleMovie.getImdbId());
        return response.isSuccess() ? response.getData() : null;
    }
}
