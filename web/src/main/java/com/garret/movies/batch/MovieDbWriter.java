package com.garret.movies.batch;

import com.garret.movies.dao.entity.Movie;
import com.garret.movies.service.api.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MovieDbWriter implements ItemWriter<Movie> {

    private final MovieService movieService;

    @Override
    public void write(List<? extends Movie> list) throws Exception {
        List<Movie> result = new ArrayList<>(list);
        movieService.saveAll(result);
    }
}
