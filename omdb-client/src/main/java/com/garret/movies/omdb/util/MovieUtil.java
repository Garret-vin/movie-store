package com.garret.movies.omdb.util;


import com.garret.movies.dao.entity.Actor;
import com.garret.movies.dao.entity.Genre;
import lombok.extern.slf4j.Slf4j;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Slf4j
public class MovieUtil {

    public static List<Actor> actorsToList(String actors) {
        String[] splitArray = actors.split(",");
        List<Actor> actorList = new ArrayList<>();
        for (String actorString : splitArray) {
            String[] fullName = actorString.trim().split(" ");
            Actor actor = new Actor(fullName[0], fullName[1]);
            actorList.add(actor);
        }
        return actorList;
    }

    public static List<Genre> genresToList(String genres) {
        String[] splitArray = genres.split(",");
        List<Genre> genreList = new ArrayList<>();
        for (String genreString : splitArray) {
            Genre genre = new Genre(genreString.trim());
            genreList.add(genre);
        }
        return genreList;
    }

    public static Integer convertVotesToInt(String votes) {
        String[] splitArray = votes.split(",");
        StringBuilder builder = new StringBuilder();
        for (String number : splitArray) {
            builder.append(number);
        }
        return Integer.parseInt(builder.toString());
    }

    public static Date parseDate(String released) {
        String pattern = "dd MMM yyyy";
        if (released.length() != pattern.length()) {
            log.warn("Release date not found, default value was set");
            return Date.valueOf("1111-11-11");
        }
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.ENGLISH);
            java.util.Date javaDate = formatter.parse(released);
            return new Date(javaDate.getTime());
        } catch (ParseException e) {
            log.error("Date parsing was failed", e);
            throw new RuntimeException("Date parsing was failed");
        }
    }
}
