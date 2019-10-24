package com.garret.movies.batch;

import com.garret.movies.dao.entity.ShortMovie;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ShortMovieReaderTest {

    @Test
    public void read() throws Exception {
        ShortMovie testMovie = new ShortMovie();
        List<ShortMovie> inputList = new ArrayList<>();
        inputList.add(testMovie);
        ShortMovieReader shortMovieReader = new ShortMovieReader(inputList);
        ShortMovie resultMovie = shortMovieReader.read();

        assertThat(resultMovie).isNotNull().isEqualTo(testMovie);
        assertThat(inputList).isEmpty();
    }

    @Test
    public void readWhenListIsEmpty() throws Exception {
        List<ShortMovie> inputList = Collections.emptyList();
        ShortMovieReader shortMovieReader = new ShortMovieReader(inputList);
        ShortMovie resultMovie = shortMovieReader.read();

        assertThat(resultMovie).isNull();
        assertThat(inputList).isEmpty();
    }
}
