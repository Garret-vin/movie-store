package com.garret.movies.omdb.impl.util;

import com.garret.movies.dao.entity.Actor;
import com.garret.movies.dao.entity.Genre;
import com.garret.movies.dao.entity.Language;
import org.junit.Test;

import java.sql.Date;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;


public class MovieUtilTest {

    @Test
    public void testActorsToList() {
        String actors = "Nikole Kidman, Arnold Schwarzenegger";

        List<Actor> expected = Arrays.asList(
                new Actor("Arnold Schwarzenegger"),
                new Actor("Nikole Kidman"));
        List<Actor> result = MovieUtil.actorsToList(actors);

        assertThat(result)
                .isNotEmpty()
                .containsAll(expected)
                .hasSameSizeAs(expected);
    }

    @Test(expected = NullPointerException.class)
    public void testActorsToList_Null_Param() {
        MovieUtil.actorsToList(null);
    }

    @Test
    public void testActorsToList_EmptyStringInput() {
        List<Actor> result = MovieUtil.actorsToList("");
        assertThat(result).isEmpty();
    }

    @Test
    public void testGenresToList() {
        String genres = "Comedy, Action";

        List<Genre> expected = Arrays.asList(new Genre("Comedy"), new Genre("Action"));
        List<Genre> result = MovieUtil.genresToList(genres);

        assertThat(result)
                .isNotEmpty()
                .containsAll(expected)
                .hasSameSizeAs(expected);
    }

    @Test(expected = NullPointerException.class)
    public void testGenresToList_Null_Param() {
        MovieUtil.genresToList(null);
    }

    @Test
    public void testGenresToList_EmptyStringInput() {
        List<Genre> result = MovieUtil.genresToList("");
        assertThat(result).isEmpty();
    }

    @Test
    public void testLanguagesToList() {
        List<String> input = Arrays.asList("English", "Urdu", "Spanish");

        List<Language> expected = Arrays.asList(
                new Language("English"),
                new Language("Urdu"),
                new Language("Spanish"));
        List<Language> result = MovieUtil.languagesToList(input);

        assertThat(result)
                .isNotEmpty()
                .containsAll(expected)
                .hasSameSizeAs(expected);
    }

    @Test(expected = NullPointerException.class)
    public void testLanguagesToList_Null_Param() {
        MovieUtil.languagesToList(null);
    }

    @Test
    public void testLanguagesToList_EmptyCollectionInput() {
        List<Language> result = MovieUtil.languagesToList(Collections.emptyList());
        assertThat(result).isEmpty();
    }

    @Test
    public void testConvertVotesToInt() {
        String input = "123,456";

        Integer expected = 123456;
        Integer result = MovieUtil.convertVotesToInt(input);

        assertThat(result).isGreaterThan(123000).isEqualTo(expected);
    }

    @Test(expected = NullPointerException.class)
    public void testConvertVotesToInt_whenInputNull() {
        MovieUtil.convertVotesToInt(null);
    }

    @Test
    public void testConvertVotesToInt_whenInputEmpty() {
        Integer result = MovieUtil.convertVotesToInt("");
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void testParseDate() {
        String input = "23 Jun 2015";

        Date expected = Date.valueOf("2015-06-23");
        Date result = MovieUtil.parseDate(input);

        assertThat(result).isInSameDayAs(expected);
    }

    @Test(expected = NullPointerException.class)
    public void testParseDate_whenInputNull() {
        MovieUtil.parseDate(null);
    }

    @Test
    public void testParseDate_whenInvalidInputParameters() {
        String params = "asdfasdsdfddw3455ty";

        Date expected = Date.valueOf("1111-11-11");
        Date result = MovieUtil.parseDate(params);

        assertThat(result).isInSameDayAs(expected);
    }

    @Test(expected = RuntimeException.class)
    public void testParseDate_whenInvalidInputParameters_butLengthIsSame() {
        String params = "asdfasdsdfd";
        MovieUtil.parseDate(params);
    }
}