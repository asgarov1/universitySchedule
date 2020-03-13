package com.asgarov.university.schedule.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.asgarov.university.schedule.domain.Professor;

import org.springframework.jdbc.core.RowMapper;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ProfessorDao extends AbstractDao<Long, Professor> {
    public Professor findById() {
        throw new NotImplementedException();
    }

    @Override protected String getUpdateQuery() {
        return null;
    }

    @Override protected String getDeleteQuery() {
        return null;
    }

    @Override protected Professor rowMapper(final ResultSet resultSet, final int rowNum) throws SQLException {
        return null;
    }

    @Override protected Map<String, ?> parameters(final Professor object) {
        return null;
    }

    @Override protected Object[] updateParameters(final Professor object) {
        return new Object[0];
    }

    @Override protected String tableName() {
        return null;
    }

    public Long create(final Professor professor) {
        throw new NotImplementedException();
    }
}
