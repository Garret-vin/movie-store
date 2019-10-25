package com.garret.movies.omdb.util;

import com.garret.movies.omdb.dto.OmdbActor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OmdbMovieUtil {

    public static List<OmdbActor> actorsToList(String actors) {
        if (actors.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.stream(actors.split(","))
                .map(String::trim)
                .map(name -> {
                    OmdbActor actor = new OmdbActor();
                    actor.setFullName(name);
                    return actor;
                }).collect(Collectors.toList());
    }
}
