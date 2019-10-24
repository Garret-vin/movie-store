package com.garret.movies.service.impl;

import com.garret.movies.dao.entity.*;
import com.garret.movies.dao.repository.MovieRepository;
import com.garret.movies.service.api.MovieService;
import com.garret.movies.service.exception.IncorrectDateException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SelectConditionStep;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.garret.movies.dao.jooq.generated.Tables.*;
import static com.garret.movies.dao.jooq.generated.tables.Actor.ACTOR;
import static com.garret.movies.dao.jooq.generated.tables.MovieActors.MOVIE_ACTORS;

@Service
@Slf4j
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private MovieRepository movieRepository;
    private DSLContext jooq;

    @Override
    @Transactional
    public Movie save(@NonNull Movie movie) {
        return movieRepository.findByImdbId(movie.getImdbId()).orElseGet(() -> {
            log.info("Saving movie " + movie.getTitle());
            return movieRepository.save(movie);
        });
    }

    @Override
    @Transactional
    public List<Movie> saveAll(@NonNull List<Movie> movies) {
        List<Movie> movieList = new ArrayList<>();
        log.info("Saving " + movies.size() + " movies to database");
        movieRepository.saveAll(movies).forEach(movieList::add);
        return movieList;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Movie> getById(@NonNull Long id) {
        return movieRepository.findById(id);
    }

    private Map<String, Date> convertYearToStartEndYearMap(int year) {
        Map<String, Date> resultMap = new HashMap<>();
        String start = year + "-01-01";
        String end = year + "-12-31";
        try {
            resultMap.put("start", formatter.parse(start));
            resultMap.put("end", formatter.parse(end));
        } catch (ParseException e) {
            log.error("Wrong year specified: " + year, e);
            throw new IncorrectDateException("Can't parse date from DB");
        }
        return resultMap;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByImdbId(@NonNull String imdbId) {
        return movieRepository.existsByImdbId(imdbId);
    }

    @Override
    @Transactional
    public boolean deleteById(@NonNull Long id) {
        boolean result = movieRepository.removeById(id) > 0;
        log.info("Delete movie with id = " + id + ": " + result);
        return result;
    }

    @Override
    @Transactional
    public void deleteAll() {
        movieRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> getAllByParams(@NonNull Map<String, String> params) {
        SelectConditionStep<Record> query = jooq.select()
                .from(MOVIE)
                .leftJoin(ACTOR.join(MOVIE_ACTORS).on(MOVIE_ACTORS.ACTORS_ID.eq(ACTOR.ID)))
                .on(MOVIE_ACTORS.MOVIE_ID.eq(MOVIE.ID))
                .leftJoin(GENRE.join(MOVIE_GENRES).on(MOVIE_GENRES.GENRES_ID.eq(GENRE.ID)))
                .on(MOVIE_GENRES.MOVIE_ID.eq(MOVIE.ID))
                .leftJoin(COUNTRY.join(MOVIE_COUNTRIES).on(MOVIE_COUNTRIES.COUNTRIES_ID.eq(COUNTRY.ID)))
                .on(MOVIE_COUNTRIES.MOVIE_ID.eq(MOVIE.ID))
                .leftJoin(LANGUAGE.join(MOVIE_LANGUAGES).on(MOVIE_LANGUAGES.LANGUAGES_ID.eq(LANGUAGE.ID)))
                .on(MOVIE_LANGUAGES.MOVIE_ID.eq(MOVIE.ID))
                .where(MOVIE.ID.isNotNull());

        if (params.containsKey("genre")) {
            query.and(GENRE.VALUE.contains(params.get("genre")));
        }
        if (params.containsKey("language")) {
            query.and(LANGUAGE.VALUE.contains(params.get("language")));
        }
        if (params.containsKey("actor")) {
            query.and(ACTOR.FULL_NAME.contains(params.get("actor")));
        }
        if (params.containsKey("year")) {
            Map<String, Date> dates = convertYearToStartEndYearMap(Integer.parseInt(params.get("year")));
            java.sql.Date start = new java.sql.Date(dates.get("start").getTime());
            java.sql.Date end = new java.sql.Date(dates.get("end").getTime());
            query.and(MOVIE.RELEASED.between(start, end));
        }
        if (params.containsKey("country")) {
            query.and(COUNTRY.NAME.contains(params.get("country")));
        }
        if (params.containsKey("top")) {
            String order = params.get("top");
            if (order.equalsIgnoreCase("votes")) {
                query.orderBy(MOVIE.IMDB_VOTES.desc());
            } else if (order.equalsIgnoreCase("rating")) {
                query.orderBy(MOVIE.IMDB_RATING.desc());
            }
        }

        Map<Record, Result<Record>> recordResultMap = query.fetch().intoGroups(MOVIE.fields());
        return recordsMapToList(recordResultMap);
    }

    private List<Movie> recordsMapToList(@NonNull Map<Record, Result<Record>> recordResultMap) {
        return recordResultMap.values()
                .stream()
                .map(res -> {
                    Movie movie = res.into(MOVIE).into(Movie.class).get(0);
                    List<Actor> actors = res.into(ACTOR)
                            .into(Actor.class)
                            .stream()
                            .distinct()
                            .collect(Collectors.toList());
                    movie.setActors(actors);
                    List<Genre> genres = res.into(GENRE)
                            .into(Genre.class)
                            .stream()
                            .distinct()
                            .collect(Collectors.toList());
                    movie.setGenres(genres);
                    List<Country> countries = res.into(COUNTRY)
                            .into(Country.class)
                            .stream()
                            .distinct()
                            .collect(Collectors.toList());
                    movie.setCountries(countries);
                    List<Language> languages = res.into(LANGUAGE)
                            .into(Language.class)
                            .stream()
                            .distinct()
                            .collect(Collectors.toList());
                    movie.setLanguages(languages);
                    return movie;
                })
                .collect(Collectors.toList());
    }
}
