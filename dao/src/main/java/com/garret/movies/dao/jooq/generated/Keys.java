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
import com.garret.movies.dao.jooq.generated.tables.records.ActorRecord;
import com.garret.movies.dao.jooq.generated.tables.records.CountryRecord;
import com.garret.movies.dao.jooq.generated.tables.records.GenreRecord;
import com.garret.movies.dao.jooq.generated.tables.records.LanguageRecord;
import com.garret.movies.dao.jooq.generated.tables.records.MovieActorsRecord;
import com.garret.movies.dao.jooq.generated.tables.records.MovieCountriesRecord;
import com.garret.movies.dao.jooq.generated.tables.records.MovieGenresRecord;
import com.garret.movies.dao.jooq.generated.tables.records.MovieLanguagesRecord;
import com.garret.movies.dao.jooq.generated.tables.records.MovieRecord;

import javax.annotation.Generated;

import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>movieStore</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.12"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<ActorRecord, Long> IDENTITY_ACTOR = Identities0.IDENTITY_ACTOR;
    public static final Identity<CountryRecord, Long> IDENTITY_COUNTRY = Identities0.IDENTITY_COUNTRY;
    public static final Identity<GenreRecord, Long> IDENTITY_GENRE = Identities0.IDENTITY_GENRE;
    public static final Identity<LanguageRecord, Long> IDENTITY_LANGUAGE = Identities0.IDENTITY_LANGUAGE;
    public static final Identity<MovieRecord, Long> IDENTITY_MOVIE = Identities0.IDENTITY_MOVIE;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<ActorRecord> KEY_ACTOR_PRIMARY = UniqueKeys0.KEY_ACTOR_PRIMARY;
    public static final UniqueKey<CountryRecord> KEY_COUNTRY_PRIMARY = UniqueKeys0.KEY_COUNTRY_PRIMARY;
    public static final UniqueKey<GenreRecord> KEY_GENRE_PRIMARY = UniqueKeys0.KEY_GENRE_PRIMARY;
    public static final UniqueKey<LanguageRecord> KEY_LANGUAGE_PRIMARY = UniqueKeys0.KEY_LANGUAGE_PRIMARY;
    public static final UniqueKey<MovieRecord> KEY_MOVIE_PRIMARY = UniqueKeys0.KEY_MOVIE_PRIMARY;
    public static final UniqueKey<MovieActorsRecord> KEY_MOVIE_ACTORS_ACTORS_ID = UniqueKeys0.KEY_MOVIE_ACTORS_ACTORS_ID;
    public static final UniqueKey<MovieCountriesRecord> KEY_MOVIE_COUNTRIES_COUNTRIES_ID = UniqueKeys0.KEY_MOVIE_COUNTRIES_COUNTRIES_ID;
    public static final UniqueKey<MovieGenresRecord> KEY_MOVIE_GENRES_GENRES_ID = UniqueKeys0.KEY_MOVIE_GENRES_GENRES_ID;
    public static final UniqueKey<MovieLanguagesRecord> KEY_MOVIE_LANGUAGES_LANGUAGES_ID = UniqueKeys0.KEY_MOVIE_LANGUAGES_LANGUAGES_ID;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<ActorRecord, Long> IDENTITY_ACTOR = Internal.createIdentity(Actor.ACTOR, Actor.ACTOR.ID);
        public static Identity<CountryRecord, Long> IDENTITY_COUNTRY = Internal.createIdentity(Country.COUNTRY, Country.COUNTRY.ID);
        public static Identity<GenreRecord, Long> IDENTITY_GENRE = Internal.createIdentity(Genre.GENRE, Genre.GENRE.ID);
        public static Identity<LanguageRecord, Long> IDENTITY_LANGUAGE = Internal.createIdentity(Language.LANGUAGE, Language.LANGUAGE.ID);
        public static Identity<MovieRecord, Long> IDENTITY_MOVIE = Internal.createIdentity(Movie.MOVIE, Movie.MOVIE.ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<ActorRecord> KEY_ACTOR_PRIMARY = Internal.createUniqueKey(Actor.ACTOR, "KEY_actor_PRIMARY", Actor.ACTOR.ID);
        public static final UniqueKey<CountryRecord> KEY_COUNTRY_PRIMARY = Internal.createUniqueKey(Country.COUNTRY, "KEY_country_PRIMARY", Country.COUNTRY.ID);
        public static final UniqueKey<GenreRecord> KEY_GENRE_PRIMARY = Internal.createUniqueKey(Genre.GENRE, "KEY_genre_PRIMARY", Genre.GENRE.ID);
        public static final UniqueKey<LanguageRecord> KEY_LANGUAGE_PRIMARY = Internal.createUniqueKey(Language.LANGUAGE, "KEY_language_PRIMARY", Language.LANGUAGE.ID);
        public static final UniqueKey<MovieRecord> KEY_MOVIE_PRIMARY = Internal.createUniqueKey(Movie.MOVIE, "KEY_movie_PRIMARY", Movie.MOVIE.ID);
        public static final UniqueKey<MovieActorsRecord> KEY_MOVIE_ACTORS_ACTORS_ID = Internal.createUniqueKey(MovieActors.MOVIE_ACTORS, "KEY_movie_actors_actors_id", MovieActors.MOVIE_ACTORS.ACTORS_ID);
        public static final UniqueKey<MovieCountriesRecord> KEY_MOVIE_COUNTRIES_COUNTRIES_ID = Internal.createUniqueKey(MovieCountries.MOVIE_COUNTRIES, "KEY_movie_countries_countries_id", MovieCountries.MOVIE_COUNTRIES.COUNTRIES_ID);
        public static final UniqueKey<MovieGenresRecord> KEY_MOVIE_GENRES_GENRES_ID = Internal.createUniqueKey(MovieGenres.MOVIE_GENRES, "KEY_movie_genres_genres_id", MovieGenres.MOVIE_GENRES.GENRES_ID);
        public static final UniqueKey<MovieLanguagesRecord> KEY_MOVIE_LANGUAGES_LANGUAGES_ID = Internal.createUniqueKey(MovieLanguages.MOVIE_LANGUAGES, "KEY_movie_languages_languages_id", MovieLanguages.MOVIE_LANGUAGES.LANGUAGES_ID);
    }
}
