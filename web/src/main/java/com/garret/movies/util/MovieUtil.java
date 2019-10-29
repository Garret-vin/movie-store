package com.garret.movies.util;

import com.garret.movies.exception.IncorrectDateException;
import com.garret.movies.service.dto.marker.Valuable;
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

    public static <T extends Valuable> List<T> stringToValuableList(String input, Class<T> clazz) {
        if (isInvalidString(input)) {
            return Collections.emptyList();
        }
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(value -> {
                    T valuable = null;
                    try {
                        valuable = clazz.newInstance();
                        valuable.setValue(value);
                    } catch (InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return valuable;
                }).collect(Collectors.toList());
    }

    public static Integer convertStringToInteger(String votes) {
        if (isInvalidString(votes)) {
            log.info("Number of votes not found, default value was set");
            return 0;
        }
        return Integer.parseInt(votes.replaceAll(",", ""));
    }

    public static Date parseDate(String released) {
        if (isInvalidString(released)) {
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

    public static Double convertStringToDouble(String imdbRating) {
        if (isInvalidString(imdbRating)) {
            log.info("Can't parse imdbRating, default value was set");
            return 0.0;
        }
        return Double.parseDouble(imdbRating);
    }

    private static boolean isInvalidString(String input) {
        return (input.isEmpty() || input.equals(NOT_ASSIGNED));
    }
}
