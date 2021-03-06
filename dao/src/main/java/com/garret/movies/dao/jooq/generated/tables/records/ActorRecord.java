/*
 * This file is generated by jOOQ.
 */
package com.garret.movies.dao.jooq.generated.tables.records;


import com.garret.movies.dao.jooq.generated.tables.Actor;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;

import javax.annotation.Generated;


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
public class ActorRecord extends UpdatableRecordImpl<ActorRecord> implements Record2<Long, String> {

    private static final long serialVersionUID = -1198104508;

    /**
     * Setter for <code>movieStore.actor.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>movieStore.actor.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>movieStore.actor.full_name</code>.
     */
    public void setFullName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>movieStore.actor.full_name</code>.
     */
    public String getFullName() {
        return (String) get(1);
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
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Long, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Long, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return Actor.ACTOR.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Actor.ACTOR.FULL_NAME;
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
        return getFullName();
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
        return getFullName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActorRecord value1(Long value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActorRecord value2(String value) {
        setFullName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActorRecord values(Long value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ActorRecord
     */
    public ActorRecord() {
        super(Actor.ACTOR);
    }

    /**
     * Create a detached, initialised ActorRecord
     */
    public ActorRecord(Long id, String fullName) {
        super(Actor.ACTOR);

        set(0, id);
        set(1, fullName);
    }
}
