package com.garret.movies.batch;

import com.garret.movies.omdb.api.OmdbClient;
import com.garret.movies.omdb.entity.ShortMovie;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@StepScope
public class ShortMovieReader implements ItemReader<ShortMovie> {

    @Value("#{jobParameters['title']}")
    private String title;
    private List<ShortMovie> movieData;
    private OmdbClient omdbClient;
    private AtomicInteger index;

    public ShortMovieReader(OmdbClient omdbClient) {
        this.omdbClient = omdbClient;
        this.movieData = new ArrayList<>();
        this.index = new AtomicInteger(0);
    }

    @BeforeStep
    void init() {
        this.movieData.addAll(omdbClient.searchMovies(title));
    }

    @Override
    public ShortMovie read() {
        return index.get() < movieData.size() ? movieData.get(index.getAndIncrement()) : null;
    }
}
