package com.garret.movies.service.util;

import com.garret.movies.service.exception.IncorrectDateException;
import com.garret.movies.service.dto.ActorDto;
import com.garret.movies.service.dto.CountryDto;
import com.garret.movies.service.dto.GenreDto;
import com.garret.movies.service.dto.LanguageDto;
import com.garret.movies.service.dto.marker.Valuable;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class MovieUtilTest {

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
    public void testParseDateWhenNotAssignedParameter() {
        String params = "N/A";

        Date expected = Date.valueOf("1111-11-11");
        Date result = MovieUtil.parseDate(params);

        assertThat(result).isInSameDayAs(expected);
    }

    @Test(expected = IncorrectDateException.class)
    public void testParseDateWhenInvalidParameter() {
        String params = "2";
        MovieUtil.parseDate(params);
    }

    @Test
    public void convertStringToDouble() {
        String input = "2.0";
        Double expected = 2.0;
        Double result = MovieUtil.convertStringToDouble(input);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void convertStringToDoubleNotAssignedParam() {
        String input = "N/A";
        Double result = MovieUtil.convertStringToDouble(input);
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void stringToActorList() {
        String input = "Arnold";
        ActorDto actorDto = new ActorDto();
        actorDto.setValue(input);
        List<ActorDto> resultList = MovieUtil.stringToValuableList(input, ActorDto::new);
        assertThat(resultList)
                .isNotNull()
                .isNotEmpty()
                .contains(actorDto);
        assertThat(resultList.get(0)).isInstanceOf(Valuable.class);
    }

    @Test
    public void stringToCountryList() {
        String input = "USA";
        CountryDto countryDto = new CountryDto();
        countryDto.setValue(input);
        List<CountryDto> resultList = MovieUtil.stringToValuableList(input, CountryDto::new);
        assertThat(resultList)
                .isNotNull()
                .isNotEmpty()
                .contains(countryDto);
        assertThat(resultList.get(0)).isInstanceOf(Valuable.class);
    }

    @Test
    public void stringToGenreList() {
        String input = "Drama";
        GenreDto genreDto = new GenreDto();
        genreDto.setValue(input);
        List<GenreDto> resultList = MovieUtil.stringToValuableList(input, GenreDto::new);
        assertThat(resultList)
                .isNotNull()
                .isNotEmpty()
                .contains(genreDto);
        assertThat(resultList.get(0)).isInstanceOf(Valuable.class);
    }

    @Test
    public void stringToLanguageList() {
        String input = "English";
        LanguageDto languageDto = new LanguageDto();
        languageDto.setValue(input);
        List<LanguageDto> resultList = MovieUtil.stringToValuableList(input, LanguageDto::new);
        assertThat(resultList)
                .isNotNull()
                .isNotEmpty()
                .contains(languageDto);
        assertThat(resultList.get(0)).isInstanceOf(Valuable.class);
    }

    @Test
    public void stringToValuableListWhenInvalidStringInput() {
        List<ActorDto> result = MovieUtil.stringToValuableList("", ActorDto::new);
        assertThat(result)
                .isNotNull()
                .isEmpty();
    }
}
