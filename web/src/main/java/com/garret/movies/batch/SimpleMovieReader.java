package com.garret.movies.batch;

import com.garret.movies.omdb.api.OmdbClient;
import com.garret.movies.omdb.dto.OmdbApiSearchResponse;
import com.garret.movies.omdb.dto.SimpleMovie;
import lombok.Data;
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
@Data
public class SimpleMovieReader implements ItemReader<SimpleMovie> {

    @Value("#{jobParameters['title']}")
    private String title;
    private List<SimpleMovie> movieData;
    private OmdbClient omdbClient;
    private AtomicInteger index;

    public SimpleMovieReader(OmdbClient omdbClient) {
        this.omdbClient = omdbClient;
        this.movieData = new ArrayList<>();
        this.index = new AtomicInteger(0);
    }

    @BeforeStep
    void init() {
        int page = 1;
        while (true) {
            OmdbApiSearchResponse<List<SimpleMovie>> response = omdbClient.searchMovies(title, page);
            if (response.isSuccess()) {
                this.movieData.addAll(response.getData());
                page++;
            } else {
                break;
            }
        }
    }

    @Override
    public SimpleMovie read() {
        int currentIndex = index.getAndIncrement();
        return currentIndex < movieData.size() ? movieData.get(currentIndex) : null;
    }
}
