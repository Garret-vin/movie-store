/*
 * This file is generated by jOOQ.
 */
package com.garret.movies.dao.jooq.generated.tables;


import com.garret.movies.dao.jooq.generated.Indexes;
import com.garret.movies.dao.jooq.generated.Moviestore;
import com.garret.movies.dao.jooq.generated.tables.records.MovieGenresRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class MovieGenres extends TableImpl<MovieGenresRecord> {

    private static final long serialVersionUID = -1199059641;

    /**
     * The reference instance of <code>movieStore.movie_genres</code>
     */
    public static final MovieGenres MOVIE_GENRES = new MovieGenres();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MovieGenresRecord> getRecordType() {
        return MovieGenresRecord.class;
    }

    /**
     * The column <code>movieStore.movie_genres.movie_id</code>.
     */
    public final TableField<MovieGenresRecord, Long> MOVIE_ID = createField("movie_id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>movieStore.movie_genres.genres_id</code>.
     */
    public final TableField<MovieGenresRecord, Long> GENRES_ID = createField("genres_id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * Create a <code>movieStore.movie_genres</code> table reference
     */
    public MovieGenres() {
        this(DSL.name("movie_genres"), null);
    }

    /**
     * Create an aliased <code>movieStore.movie_genres</code> table reference
     */
    public MovieGenres(String alias) {
        this(DSL.name(alias), MOVIE_GENRES);
    }

    /**
     * Create an aliased <code>movieStore.movie_genres</code> table reference
     */
    public MovieGenres(Name alias) {
        this(alias, MOVIE_GENRES);
    }

    private MovieGenres(Name alias, Table<MovieGenresRecord> aliased) {
        this(alias, aliased, null);
    }

    private MovieGenres(Name alias, Table<MovieGenresRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> MovieGenres(Table<O> child, ForeignKey<O, MovieGenresRecord> key) {
        super(child, key, MOVIE_GENRES);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Moviestore.MOVIESTORE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.MOVIE_GENRES_MOVIE_GENRES_INDEX);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieGenres as(String alias) {
        return new MovieGenres(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieGenres as(Name alias) {
        return new MovieGenres(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public MovieGenres rename(String name) {
        return new MovieGenres(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public MovieGenres rename(Name name) {
        return new MovieGenres(name, null);
    }
}
