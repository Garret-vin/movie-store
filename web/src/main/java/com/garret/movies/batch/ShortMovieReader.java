package com.garret.movies.batch;

import com.garret.movies.omdb.api.OmdbClient;
import com.garret.movies.omdb.entity.ShortMovie;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@StepScope
public class ShortMovieReader implements ItemReader<ShortMovie> {

    @Value("#{jobParameters['title']}")
    private String title;
    private List<ShortMovie> movieData;
    private OmdbClient omdbClient;

    public ShortMovieReader(OmdbClient omdbClient) {
        this.omdbClient = omdbClient;
        this.movieData = new ArrayList<>();
    }

    @BeforeStep
    void init() {
        this.movieData.addAll(omdbClient.searchMovies(title));
    }

    @Override
    public ShortMovie read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        ShortMovie shortMovie = null;

        if (!movieData.isEmpty()) {
            shortMovie = movieData.remove(0);
        }

        return shortMovie;
    }
}
