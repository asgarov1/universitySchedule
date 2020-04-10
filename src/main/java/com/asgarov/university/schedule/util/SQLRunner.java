package com.asgarov.university.schedule.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class SQLRunner {

    private final DataSource dataSource;

    public SQLRunner(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void runSQL(String fileName) {
        Resource resource = new ClassPathResource(fileName);
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(resource);
        databasePopulator.execute(dataSource);
    }
}
