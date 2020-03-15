package com.asgarov.university.schedule.data;

import javax.annotation.PostConstruct;

import com.asgarov.university.schedule.util.SQLRunner;

import org.springframework.stereotype.Component;

@Component
public class DataGenerator {

    public static final String CREATE_TABLES_SQL = "sql/createTables.sql";
    public static final String POPULATE_TABLES_SQL = "sql/populateTables.sql";
    private SQLRunner sqlRunner;

    public DataGenerator(final SQLRunner sqlRunner) {
        this.sqlRunner = sqlRunner;
    }

    @PostConstruct
    private void init() {
        sqlRunner.runSQL(CREATE_TABLES_SQL);
        sqlRunner.runSQL(POPULATE_TABLES_SQL);
    }
}
