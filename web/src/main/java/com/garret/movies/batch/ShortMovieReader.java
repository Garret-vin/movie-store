package com.garret.movies.batch;

import com.garret.movies.dao.entity.ShortMovie;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.List;

@RequiredArgsConstructor
public class ShortMovieReader implements ItemReader<ShortMovie> {

    private final List<ShortMovie> movieData;

    @Override
    public ShortMovie read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        ShortMovie shortMovie = null;

        if (!movieData.isEmpty()) {
            shortMovie = movieData.remove(0);
        }

        return shortMovie;
    }
}
