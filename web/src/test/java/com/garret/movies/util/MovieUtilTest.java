package com.garret.movies.util;

import com.garret.movies.exception.IncorrectDateException;
import com.garret.movies.service.dto.ActorDto;
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
    public void stringToValuableList() {
        String input = "Arnold";
        ActorDto actorDto = new ActorDto();
        actorDto.setValue(input);
        List<ActorDto> resultList = MovieUtil.stringToValuableList(input, ActorDto.class);
        assertThat(resultList)
                .isNotNull()
                .isNotEmpty()
                .contains(actorDto);
        assertThat(resultList.get(0)).isInstanceOf(Valuable.class);
    }

    @Test
    public void stringToValuableListWhenInvalidStringInput() {
        List<ActorDto> result = MovieUtil.stringToValuableList("", ActorDto.class);
        assertThat(result)
                .isNotNull()
                .isEmpty();
    }
}
