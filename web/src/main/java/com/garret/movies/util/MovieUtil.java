package com.garret.movies.util;

import com.garret.movies.exception.IncorrectDateException;
import com.garret.movies.service.dto.ActorDto;
import com.garret.movies.service.dto.CountryDto;
import com.garret.movies.service.dto.GenreDto;
import com.garret.movies.service.dto.LanguageDto;
import lombok.extern.slf4j.Slf4j;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
public class MovieUtil {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
    private static final String NOT_ASSIGNED = "N/A";

    public static List<ActorDto> actorsToList(String actors) {
        if (actors.isEmpty() || actors.equals(NOT_ASSIGNED)) {
            return Collections.emptyList();
        }
        return Arrays.stream(actors.split(","))
                .map(String::trim)
                .map(name -> {
                    ActorDto actor = new ActorDto();
                    actor.setFullName(name);
                    return actor;
                }).collect(Collectors.toList());
    }

    public static List<GenreDto> genresToList(String genres) {
        if (genres.isEmpty() || genres.equals(NOT_ASSIGNED)) {
            return Collections.emptyList();
        }
        return Arrays.stream(genres.split(","))
                .map(String::trim)
                .map(genreTitle -> {
                    GenreDto genre = new GenreDto();
                    genre.setValue(genreTitle);
                    return genre;
                }).collect(Collectors.toList());
    }

    public static List<LanguageDto> languagesToList(String languages) {
        if (languages.isEmpty() || languages.equals(NOT_ASSIGNED)) {
            return Collections.emptyList();
        }
        return Arrays.stream(languages.split(","))
                .map(String::trim)
                .map(lang -> {
                    LanguageDto language = new LanguageDto();
                    language.setValue(lang);
                    return language;
                }).collect(Collectors.toList());
    }

    public static Integer convertStringToInteger(String votes) {
        if (votes.isEmpty() || votes.equals(NOT_ASSIGNED)) {
            log.info("Number of votes not found, default value was set");
            return 0;
        }
        return Integer.parseInt(votes.replaceAll(",", ""));
    }

    public static Date parseDate(String released) {
        if (released.isEmpty() || released.equals(NOT_ASSIGNED)) {
            log.info("Release date not found, default value was set");
            return Date.valueOf("1111-11-11");
        }
        try {
            return new Date(formatter.parse(released).getTime());
        } catch (ParseException e) {
            log.warn("Can't parse date", e);
            throw new IncorrectDateException("Date parsing was failed");
        }
    }

    public static List<CountryDto> countiesToList(String counties) {
        if (counties.isEmpty() || counties.equals(NOT_ASSIGNED)) {
            return Collections.emptyList();
        }
        return Arrays.stream(counties.split(","))
                .map(String::trim)
                .map(c -> {
                    CountryDto country = new CountryDto();
                    country.setName(c);
                    return country;
                }).collect(Collectors.toList());

    }

    public static Double convertStringToDouble(String imdbRating) {
        if (imdbRating.isEmpty() || imdbRating.equals(NOT_ASSIGNED)) {
            log.info("Can't parse imdbRating, default value was set");
            return 0.0;
        }
        return Double.parseDouble(imdbRating);
    }
}
