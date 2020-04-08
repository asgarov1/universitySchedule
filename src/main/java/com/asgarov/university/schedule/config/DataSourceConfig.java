package com.asgarov.university.schedule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jndi.JndiTemplate;

import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource myDataSource() throws NamingException {
        JndiTemplate jndiTemplate = new JndiTemplate();
        return (DataSource) jndiTemplate.lookup("java:comp/env/jdbc/universityDB");
    }

    @Bean
    public SQLErrorCodeSQLExceptionTranslator SQLExceptionTranslator() {
        return new SQLErrorCodeSQLExceptionTranslator();
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws NamingException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(myDataSource());
        jdbcTemplate.setExceptionTranslator(SQLExceptionTranslator());
        return jdbcTemplate;
    }
}
