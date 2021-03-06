/*
 * This file is generated by jOOQ.
 */
package com.garret.movies.dao.jooq.generated.tables.records;


import com.garret.movies.dao.jooq.generated.tables.Movie;

import java.sql.Date;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record10;
import org.jooq.Row10;
import org.jooq.impl.UpdatableRecordImpl;


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
public class MovieRecord extends UpdatableRecordImpl<MovieRecord> implements Record10<Long, String, String, Double, Integer, String, Date, String, String, String> {

    private static final long serialVersionUID = -922916369;

    /**
     * Setter for <code>movieStore.movie.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>movieStore.movie.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>movieStore.movie.director</code>.
     */
    public void setDirector(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>movieStore.movie.director</code>.
     */
    public String getDirector() {
        return (String) get(1);
    }

    /**
     * Setter for <code>movieStore.movie.imdb_id</code>.
     */
    public void setImdbId(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>movieStore.movie.imdb_id</code>.
     */
    public String getImdbId() {
        return (String) get(2);
    }

    /**
     * Setter for <code>movieStore.movie.imdb_rating</code>.
     */
    public void setImdbRating(Double value) {
        set(3, value);
    }

    /**
     * Getter for <code>movieStore.movie.imdb_rating</code>.
     */
    public Double getImdbRating() {
        return (Double) get(3);
    }

    /**
     * Setter for <code>movieStore.movie.imdb_votes</code>.
     */
    public void setImdbVotes(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>movieStore.movie.imdb_votes</code>.
     */
    public Integer getImdbVotes() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>movieStore.movie.plot</code>.
     */
    public void setPlot(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>movieStore.movie.plot</code>.
     */
    public String getPlot() {
        return (String) get(5);
    }

    /**
     * Setter for <code>movieStore.movie.released</code>.
     */
    public void setReleased(Date value) {
        set(6, value);
    }

    /**
     * Getter for <code>movieStore.movie.released</code>.
     */
    public Date getReleased() {
        return (Date) get(6);
    }

    /**
     * Setter for <code>movieStore.movie.runtime</code>.
     */
    public void setRuntime(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>movieStore.movie.runtime</code>.
     */
    public String getRuntime() {
        return (String) get(7);
    }

    /**
     * Setter for <code>movieStore.movie.title</code>.
     */
    public void setTitle(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>movieStore.movie.title</code>.
     */
    public String getTitle() {
        return (String) get(8);
    }

    /**
     * Setter for <code>movieStore.movie.type</code>.
     */
    public void setType(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>movieStore.movie.type</code>.
     */
    public String getType() {
        return (String) get(9);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record10 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row10<Long, String, String, Double, Integer, String, Date, String, String, String> fieldsRow() {
        return (Row10) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row10<Long, String, String, Double, Integer, String, Date, String, String, String> valuesRow() {
        return (Row10) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return Movie.MOVIE.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Movie.MOVIE.DIRECTOR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Movie.MOVIE.IMDB_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Double> field4() {
        return Movie.MOVIE.IMDB_RATING;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field5() {
        return Movie.MOVIE.IMDB_VOTES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return Movie.MOVIE.PLOT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Date> field7() {
        return Movie.MOVIE.RELEASED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return Movie.MOVIE.RUNTIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field9() {
        return Movie.MOVIE.TITLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field10() {
        return Movie.MOVIE.TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getDirector();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getImdbId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double component4() {
        return getImdbRating();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component5() {
        return getImdbVotes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getPlot();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date component7() {
        return getReleased();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component8() {
        return getRuntime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component9() {
        return getTitle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component10() {
        return getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getDirector();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getImdbId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double value4() {
        return getImdbRating();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value5() {
        return getImdbVotes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getPlot();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date value7() {
        return getReleased();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getRuntime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value9() {
        return getTitle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value10() {
        return getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieRecord value1(Long value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieRecord value2(String value) {
        setDirector(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieRecord value3(String value) {
        setImdbId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieRecord value4(Double value) {
        setImdbRating(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieRecord value5(Integer value) {
        setImdbVotes(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieRecord value6(String value) {
        setPlot(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieRecord value7(Date value) {
        setReleased(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieRecord value8(String value) {
        setRuntime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieRecord value9(String value) {
        setTitle(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieRecord value10(String value) {
        setType(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieRecord values(Long value1, String value2, String value3, Double value4, Integer value5, String value6, Date value7, String value8, String value9, String value10) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached MovieRecord
     */
    public MovieRecord() {
        super(Movie.MOVIE);
    }

    /**
     * Create a detached, initialised MovieRecord
     */
    public MovieRecord(Long id, String director, String imdbId, Double imdbRating, Integer imdbVotes, String plot, Date released, String runtime, String title, String type) {
        super(Movie.MOVIE);

        set(0, id);
        set(1, director);
        set(2, imdbId);
        set(3, imdbRating);
        set(4, imdbVotes);
        set(5, plot);
        set(6, released);
        set(7, runtime);
        set(8, title);
        set(9, type);
    }
}
