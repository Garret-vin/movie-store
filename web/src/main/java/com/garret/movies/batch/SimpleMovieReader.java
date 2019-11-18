package com.garret.movies.batch;

import com.garret.movies.omdb.api.OmdbClient;
import com.garret.movies.omdb.dto.OmdbApiSearchResponse;
import com.garret.movies.omdb.dto.SimpleMovie;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.AbstractPagingItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@StepScope
public class SimpleMovieReader extends AbstractPagingItemReader<SimpleMovie> {

    @Value("#{jobParameters['title']}")
    private String title;
    private OmdbClient omdbClient;

    public SimpleMovieReader(OmdbClient omdbClient) {
        this.omdbClient = omdbClient;
    }

    @Override
    protected void doReadPage() {
        if (results == null) {
            results = new ArrayList<>();
        } else {
            results.clear();
        }

        OmdbApiSearchResponse<List<SimpleMovie>> response = omdbClient.searchMovies(title, getPage());
        if (response.isSuccess()) {
            results.addAll(response.getData());
        }
    }

    @Override
    protected void doJumpToPage(int i) {

    }
}
