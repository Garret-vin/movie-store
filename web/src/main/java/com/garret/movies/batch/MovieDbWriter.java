package com.garret.movies.batch;

import com.garret.movies.omdb.dto.OmdbMovie;
import com.garret.movies.service.api.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@StepScope
@RequiredArgsConstructor
public class MovieDbWriter implements ItemWriter<OmdbMovie> {

    private final MovieService movieService;

    @Override
    public void write(List<? extends OmdbMovie> list) throws Exception {
        List<OmdbMovie> result = new ArrayList<>(list);
//        movieService.saveAll(result);TODO
    }
}
