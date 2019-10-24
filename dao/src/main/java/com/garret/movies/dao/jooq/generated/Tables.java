/*
 * This file is generated by jOOQ.
 */
package com.garret.movies.dao.jooq.generated;


import com.garret.movies.dao.jooq.generated.tables.Actor;
import com.garret.movies.dao.jooq.generated.tables.Country;
import com.garret.movies.dao.jooq.generated.tables.Genre;
import com.garret.movies.dao.jooq.generated.tables.Language;
import com.garret.movies.dao.jooq.generated.tables.Movie;
import com.garret.movies.dao.jooq.generated.tables.MovieActors;
import com.garret.movies.dao.jooq.generated.tables.MovieCountries;
import com.garret.movies.dao.jooq.generated.tables.MovieGenres;
import com.garret.movies.dao.jooq.generated.tables.MovieLanguages;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in movieStore
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.12"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>movieStore.actor</code>.
     */
    public static final Actor ACTOR = com.garret.movies.dao.jooq.generated.tables.Actor.ACTOR;

    /**
     * The table <code>movieStore.country</code>.
     */
    public static final Country COUNTRY = com.garret.movies.dao.jooq.generated.tables.Country.COUNTRY;

    /**
     * The table <code>movieStore.genre</code>.
     */
    public static final Genre GENRE = com.garret.movies.dao.jooq.generated.tables.Genre.GENRE;

    /**
     * The table <code>movieStore.language</code>.
     */
    public static final Language LANGUAGE = com.garret.movies.dao.jooq.generated.tables.Language.LANGUAGE;

    /**
     * The table <code>movieStore.movie</code>.
     */
    public static final Movie MOVIE = com.garret.movies.dao.jooq.generated.tables.Movie.MOVIE;

    /**
     * The table <code>movieStore.movie_actors</code>.
     */
    public static final MovieActors MOVIE_ACTORS = com.garret.movies.dao.jooq.generated.tables.MovieActors.MOVIE_ACTORS;

    /**
     * The table <code>movieStore.movie_countries</code>.
     */
    public static final MovieCountries MOVIE_COUNTRIES = com.garret.movies.dao.jooq.generated.tables.MovieCountries.MOVIE_COUNTRIES;

    /**
     * The table <code>movieStore.movie_genres</code>.
     */
    public static final MovieGenres MOVIE_GENRES = com.garret.movies.dao.jooq.generated.tables.MovieGenres.MOVIE_GENRES;

    /**
     * The table <code>movieStore.movie_languages</code>.
     */
    public static final MovieLanguages MOVIE_LANGUAGES = com.garret.movies.dao.jooq.generated.tables.MovieLanguages.MOVIE_LANGUAGES;
}
