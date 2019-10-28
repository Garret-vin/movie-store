package com.garret.movies.util;

import com.garret.movies.service.dto.ActorDto;
import com.garret.movies.service.dto.CountryDto;
import com.garret.movies.service.dto.GenreDto;
import com.garret.movies.service.dto.LanguageDto;
import org.junit.Test;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class MovieUtilTest {

    @Test
    public void testActorsToList() {
        String actors = "Nikole Kidman, Arnold Schwarzenegger";

        ActorDto arni = new ActorDto();
        arni.setFullName("Arnold Schwarzenegger");
        ActorDto niki = new ActorDto();
        niki.setFullName("Nikole Kidman");
        List<ActorDto> expected = Arrays.asList(arni, niki);
        List<ActorDto> result = MovieUtil.actorsToList(actors);

        assertThat(result)
                .isNotEmpty()
                .containsAll(expected)
                .hasSameSizeAs(expected);
    }

    @Test
    public void testActorsToListWhenEmptyStringInput() {
        List<ActorDto> result = MovieUtil.actorsToList("");
        assertThat(result).isEmpty();
    }

    @Test
    public void testGenresToList() {
        String genres = "Comedy, Action";
        GenreDto comedy = new GenreDto();
        comedy.setValue("Comedy");
        GenreDto action = new GenreDto();
        action.setValue("Action");
        List<GenreDto> expected = Arrays.asList(comedy, action);
        List<GenreDto> result = MovieUtil.genresToList(genres);

        assertThat(result)
                .isNotEmpty()
                .containsAll(expected)
                .hasSameSizeAs(expected);
    }

    @Test
    public void testGenresToListWhenEmptyStringInput() {
        List<GenreDto> result = MovieUtil.genresToList("");
        assertThat(result).isEmpty();
    }

    @Test
    public void testLanguagesToList() {
        String input = "English, Urdu, Spanish";
        LanguageDto english = new LanguageDto();
        english.setValue("English");
        LanguageDto urdu = new LanguageDto();
        urdu.setValue("Urdu");
        LanguageDto spanish = new LanguageDto();
        spanish.setValue("Spanish");
        List<LanguageDto> expected = Arrays.asList(english, urdu, spanish);
        List<LanguageDto> result = MovieUtil.languagesToList(input);

        assertThat(result)
                .isNotEmpty()
                .containsAll(expected)
                .hasSameSizeAs(expected);
    }

    @Test
    public void testLanguagesToListWhenEmptyCollectionInput() {
        List<LanguageDto> result = MovieUtil.languagesToList("");
        assertThat(result).isEmpty();
    }

    @Test
    public void testConvertVotesToInt() {
        String input = "123,456";

        Integer expected = 123456;
        Integer result = MovieUtil.convertStringToInteger(input);

        assertThat(result).isGreaterThan(123000).isEqualTo(expected);
    }

    @Test
    public void testConvertVotesToIntWhenInputIsEmpty() {
        Integer result = MovieUtil.convertStringToInteger("");
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
        String params = "N/A";

        Date expected = Date.valueOf("1111-11-11");
        Date result = MovieUtil.parseDate(params);

        assertThat(result).isInSameDayAs(expected);
    }

    @Test
    public void testCountiesToList() {
        String input = "USA, India";
        CountryDto usa = new CountryDto();
        usa.setName("USA");
        CountryDto india = new CountryDto();
        india.setName("India");

        List<CountryDto> expected = Arrays.asList(usa, india);
        List<CountryDto> result = MovieUtil.countiesToList(input);

        assertThat(result)
                .isNotEmpty()
                .containsAll(expected)
                .hasSameSizeAs(expected);
    }

    @Test
    public void testCountriesToListWhenEmptyCollectionInput() {
        List<CountryDto> result = MovieUtil.countiesToList("");
        assertThat(result).isEmpty();
    }
}
