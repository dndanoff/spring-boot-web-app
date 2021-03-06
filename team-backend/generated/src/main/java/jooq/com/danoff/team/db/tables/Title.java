/*
 * This file is generated by jOOQ.
 */
package com.danoff.team.db.tables;


import com.danoff.team.db.Indexes;
import com.danoff.team.db.Keys;
import com.danoff.team.db.Public;
import com.danoff.team.db.tables.records.TitleRecord;

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
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Title extends TableImpl<TitleRecord> {

    private static final long serialVersionUID = 861627500;

    /**
     * The reference instance of <code>PUBLIC.TITLE</code>
     */
    public static final Title TITLE = new Title();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TitleRecord> getRecordType() {
        return TitleRecord.class;
    }

    /**
     * The column <code>PUBLIC.TITLE.ID</code>.
     */
    public final TableField<TitleRecord, Long> ID = createField("ID", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.TITLE.NAME</code>.
     */
    public final TableField<TitleRecord, String> NAME = createField("NAME", org.jooq.impl.SQLDataType.VARCHAR(200).nullable(false), this, "");

    /**
     * The column <code>PUBLIC.TITLE.DESCRIPTION</code>.
     */
    public final TableField<TitleRecord, String> DESCRIPTION = createField("DESCRIPTION", org.jooq.impl.SQLDataType.VARCHAR(500).nullable(false), this, "");

    /**
     * Create a <code>PUBLIC.TITLE</code> table reference
     */
    public Title() {
        this(DSL.name("TITLE"), null);
    }

    /**
     * Create an aliased <code>PUBLIC.TITLE</code> table reference
     */
    public Title(String alias) {
        this(DSL.name(alias), TITLE);
    }

    /**
     * Create an aliased <code>PUBLIC.TITLE</code> table reference
     */
    public Title(Name alias) {
        this(alias, TITLE);
    }

    private Title(Name alias, Table<TitleRecord> aliased) {
        this(alias, aliased, null);
    }

    private Title(Name alias, Table<TitleRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Title(Table<O> child, ForeignKey<O, TitleRecord> key) {
        super(child, key, TITLE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.CONSTRAINT_INDEX_4, Indexes.CONSTRAINT_INDEX_4C, Indexes.CONSTRAINT_INDEX_4C2, Indexes.PRIMARY_KEY_4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<TitleRecord> getPrimaryKey() {
        return Keys.TITLE_PK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TitleRecord>> getKeys() {
        return Arrays.<UniqueKey<TitleRecord>>asList(Keys.TITLE_PK, Keys.CONSTRAINT_4, Keys.CONSTRAINT_4C, Keys.CONSTRAINT_4C2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Title as(String alias) {
        return new Title(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Title as(Name alias) {
        return new Title(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Title rename(String name) {
        return new Title(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Title rename(Name name) {
        return new Title(name, null);
    }
}
