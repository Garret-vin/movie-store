package com.garret.movies.omdb.impl.util;


import com.garret.movies.dao.entity.Actor;
import com.garret.movies.dao.entity.Genre;
import com.garret.movies.dao.entity.Language;
import lombok.NonNull;
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

    public static List<Actor> actorsToList(@NonNull String actors) {
        if (actors.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.stream(actors.split(","))
                .map(String::trim)
                .map(Actor::new)
                .collect(Collectors.toList());
    }

    public static List<Genre> genresToList(@NonNull String genres) {
        if (genres.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.stream(genres.split(","))
                .map(String::trim)
                .map(Genre::new)
                .collect(Collectors.toList());
    }

    public static List<Language> languagesToList(@NonNull List<String> languages) {
        return languages.stream()
                .map(String::trim)
                .map(Language::new)
                .collect(Collectors.toList());
    }

    public static Integer convertVotesToInt(@NonNull String votes) {
        if (votes.isEmpty()) {
            return 0;
        }
        String[] splitArray = votes.split(",");
        StringBuilder builder = new StringBuilder();
        for (String number : splitArray) {
            builder.append(number);
        }
        return Integer.parseInt(builder.toString());
    }

    public static Date parseDate(@NonNull String released) {
        String pattern = "dd MMM yyyy";
        if (released.length() != pattern.length()) {
            log.info("Release date not found, default value was set");
            return Date.valueOf("1111-11-11");
        }
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.ENGLISH);
            java.util.Date javaDate = formatter.parse(released);
            return new Date(javaDate.getTime());
        } catch (ParseException e) {
            log.warn("Can't parse date", e);
            throw new RuntimeException("Date parsing was failed");
        }
    }
}
