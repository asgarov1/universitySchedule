package com.asgarov.university.schedule.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.asgarov.university.schedule.domain.Student;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao extends AbstractDao<Long, Student> {

    @Override protected String tableName() {
        return null;
    }

    @Override protected String getUpdateQuery() {
        return null;
    }

    @Override protected String getDeleteQuery() {
        return null;
    }


    @Override protected Student rowMapper(final ResultSet resultSet, final int rowNum) throws SQLException {
        return null;
    }

    @Override protected Map<String, ?> parameters(final Student object) {
        return null;
    }

    @Override protected String getFindAllQuery() {
        return null;
    }

    @Override protected Object[] updateParameters(final Student object) {
        return new Object[] {};
    }
}
