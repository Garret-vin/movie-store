/*
 * This file is generated by jOOQ.
 */
package com.garret.movies.dao.jooq.generated.tables;


import com.garret.movies.dao.jooq.generated.Indexes;
import com.garret.movies.dao.jooq.generated.Moviestore;
import com.garret.movies.dao.jooq.generated.tables.records.MovieLanguagesRecord;

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
public class MovieLanguages extends TableImpl<MovieLanguagesRecord> {

    private static final long serialVersionUID = 854220773;

    /**
     * The reference instance of <code>movieStore.movie_languages</code>
     */
    public static final MovieLanguages MOVIE_LANGUAGES = new MovieLanguages();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MovieLanguagesRecord> getRecordType() {
        return MovieLanguagesRecord.class;
    }

    /**
     * The column <code>movieStore.movie_languages.movie_id</code>.
     */
    public final TableField<MovieLanguagesRecord, Long> MOVIE_ID = createField("movie_id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>movieStore.movie_languages.languages_id</code>.
     */
    public final TableField<MovieLanguagesRecord, Long> LANGUAGES_ID = createField("languages_id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * Create a <code>movieStore.movie_languages</code> table reference
     */
    public MovieLanguages() {
        this(DSL.name("movie_languages"), null);
    }

    /**
     * Create an aliased <code>movieStore.movie_languages</code> table reference
     */
    public MovieLanguages(String alias) {
        this(DSL.name(alias), MOVIE_LANGUAGES);
    }

    /**
     * Create an aliased <code>movieStore.movie_languages</code> table reference
     */
    public MovieLanguages(Name alias) {
        this(alias, MOVIE_LANGUAGES);
    }

    private MovieLanguages(Name alias, Table<MovieLanguagesRecord> aliased) {
        this(alias, aliased, null);
    }

    private MovieLanguages(Name alias, Table<MovieLanguagesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> MovieLanguages(Table<O> child, ForeignKey<O, MovieLanguagesRecord> key) {
        super(child, key, MOVIE_LANGUAGES);
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
        return Arrays.<Index>asList(Indexes.MOVIE_LANGUAGES_MOVIE_LANGUAGES_INDEX);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieLanguages as(String alias) {
        return new MovieLanguages(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieLanguages as(Name alias) {
        return new MovieLanguages(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public MovieLanguages rename(String name) {
        return new MovieLanguages(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public MovieLanguages rename(Name name) {
        return new MovieLanguages(name, null);
    }
}
