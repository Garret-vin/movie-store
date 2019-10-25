package com.garret.movies.service.util;

import com.garret.movies.dao.exception.IncorrectDateException;
import com.garret.movies.service.dto.ActorDto;
import com.garret.movies.service.dto.CountryDto;
import com.garret.movies.service.dto.GenreDto;
import com.garret.movies.service.dto.LanguageDto;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public class MovieUtil {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
    private static final Pattern DATE_PATTERN =
            Pattern.compile("^(([0-9])|([0-2][0-9])|([3][0-1])) (Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) \\d{4}$");
    private static final Pattern DOUBLE_PATTERN =
            Pattern.compile("[0-9]{1,13}(\\.[0-9]*)?");

    public static List<ActorDto> actorsToList(String actors) {
        if (actors.isEmpty()) {
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
        if (genres.isEmpty()) {
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
        if (languages.isEmpty()) {
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
        if (votes.isEmpty() || votes.equals("N/A")) {
            log.info("Number of votes not found, default value was set");
            return 0;
        }
        String[] splitArray = votes.split(",");
        StringBuilder builder = new StringBuilder();
        for (String number : splitArray) {
            builder.append(number);
        }
        return Integer.parseInt(builder.toString());
    }

    public static Date parseDate(String released) {
        if (!DATE_PATTERN.matcher(released).matches()) {
            log.info("Release date not found, default value was set");
            return Date.valueOf("1111-11-11");
        }
        try {
            java.util.Date javaDate = formatter.parse(released);
            return new Date(javaDate.getTime());
        } catch (ParseException e) {
            log.warn("Can't parse date", e);
            throw new IncorrectDateException("Date parsing was failed");
        }
    }

    public static List<CountryDto> countiesToList(String counties) {
        if (counties.isEmpty()) {
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
        if (!DOUBLE_PATTERN.matcher(imdbRating).matches()) {
            log.info("Can't parse imdbRating, default value was set");
            return 0.0;
        }
        return Double.parseDouble(imdbRating);
    }
}