/*
 * This file is generated by jOOQ.
 */
package com.garret.movies.dao.jooq.generated.tables.records;


import com.garret.movies.dao.jooq.generated.tables.MovieCountries;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.12"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MovieCountriesRecord extends TableRecordImpl<MovieCountriesRecord> implements Record2<Long, Long> {

    private static final long serialVersionUID = -762803620;

    /**
     * Setter for <code>movieStore.movie_countries.movie_id</code>.
     */
    public void setMovieId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>movieStore.movie_countries.movie_id</code>.
     */
    public Long getMovieId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>movieStore.movie_countries.countries_id</code>.
     */
    public void setCountriesId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>movieStore.movie_countries.countries_id</code>.
     */
    public Long getCountriesId() {
        return (Long) get(1);
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Long, Long> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Long, Long> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return MovieCountries.MOVIE_COUNTRIES.MOVIE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field2() {
        return MovieCountries.MOVIE_COUNTRIES.COUNTRIES_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component1() {
        return getMovieId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component2() {
        return getCountriesId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getMovieId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value2() {
        return getCountriesId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieCountriesRecord value1(Long value) {
        setMovieId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieCountriesRecord value2(Long value) {
        setCountriesId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieCountriesRecord values(Long value1, Long value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached MovieCountriesRecord
     */
    public MovieCountriesRecord() {
        super(MovieCountries.MOVIE_COUNTRIES);
    }

    /**
     * Create a detached, initialised MovieCountriesRecord
     */
    public MovieCountriesRecord(Long movieId, Long countriesId) {
        super(MovieCountries.MOVIE_COUNTRIES);

        set(0, movieId);
        set(1, countriesId);
    }
}
