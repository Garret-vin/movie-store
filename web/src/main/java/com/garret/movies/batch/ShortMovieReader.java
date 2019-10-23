package com.garret.movies.batch;

import com.garret.movies.dao.entity.ShortMovie;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ShortMovieReader implements ItemReader<ShortMovie> {

    private List<ShortMovie> movieData;

    @Override
    public ShortMovie read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        ShortMovie shortMovie = null;

        if (!movieData.isEmpty()) {
            shortMovie = movieData.remove(0);
        }

        return shortMovie;
    }
}
