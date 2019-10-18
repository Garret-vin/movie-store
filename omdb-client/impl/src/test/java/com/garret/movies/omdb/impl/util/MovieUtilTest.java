package com.garret.movies.omdb.impl.util;

import com.garret.movies.dao.entity.Actor;
import com.garret.movies.dao.entity.Genre;
import com.garret.movies.dao.entity.Language;
import org.junit.Test;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MovieUtilTest {

    @Test
    public void testActorsToList() {
        String actors = "Nikole Kidman, Arnold Schwarzenegger";

        Actor arni = new Actor();
        arni.setFullName("Arnold Schwarzenegger");
        Actor niki = new Actor();
        niki.setFullName("Nikole Kidman");
        List<Actor> expected = Arrays.asList(arni, niki);
        List<Actor> result = MovieUtil.actorsToList(actors);

        assertThat(result)
                .isNotEmpty()
                .containsAll(expected)
                .hasSameSizeAs(expected);
    }

    @Test
    public void testActorsToListWhenEmptyStringInput() {
        List<Actor> result = MovieUtil.actorsToList("");
        assertThat(result).isEmpty();
    }

    @Test
    public void testGenresToList() {
        String genres = "Comedy, Action";
        Genre comedy = new Genre();
        comedy.setValue("Comedy");
        Genre action = new Genre();
        action.setValue("Action");
        List<Genre> expected = Arrays.asList(comedy, action);
        List<Genre> result = MovieUtil.genresToList(genres);

        assertThat(result)
                .isNotEmpty()
                .containsAll(expected)
                .hasSameSizeAs(expected);
    }

    @Test
    public void testGenresToListWhenEmptyStringInput() {
        List<Genre> result = MovieUtil.genresToList("");
        assertThat(result).isEmpty();
    }

    @Test
    public void testLanguagesToList() {
        List<String> input = Arrays.asList("English", "Urdu", "Spanish");
        Language english = new Language();
        english.setValue("English");
        Language urdu = new Language();
        urdu.setValue("Urdu");
        Language spanish = new Language();
        spanish.setValue("Spanish");
        List<Language> expected = Arrays.asList(english, urdu, spanish);
        List<Language> result = MovieUtil.languagesToList(input);

        assertThat(result)
                .isNotEmpty()
                .containsAll(expected)
                .hasSameSizeAs(expected);
    }

    @Test
    public void testLanguagesToListWhenEmptyCollectionInput() {
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

    @Test
    public void testConvertVotesToIntWhenInputIsEmpty() {
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

    @Test
    public void testParseDateWhenInvalidInputParameters() {
        String params = "asdfasdsdfddw3455ty";

        Date expected = Date.valueOf("1111-11-11");
        Date result = MovieUtil.parseDate(params);

        assertThat(result).isInSameDayAs(expected);
    }
}