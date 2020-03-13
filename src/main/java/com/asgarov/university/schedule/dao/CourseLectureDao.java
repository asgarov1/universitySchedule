package com.asgarov.university.schedule.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.asgarov.university.schedule.domain.CourseLecture;

import org.springframework.jdbc.core.RowMapper;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class CourseLectureDao extends AbstractDao<Long, CourseLecture> {
    public Long save(final CourseLecture courseLecture) {
        throw new NotImplementedException();
    }

    @Override protected String getUpdateQuery() {
        return null;
    }

    @Override protected String getDeleteQuery() {
        return null;
    }

    @Override protected CourseLecture rowMapper(final ResultSet resultSet, final int rowNum) throws SQLException {
        return null;
    }

    @Override protected Map<String, ?> parameters(final CourseLecture object) {
        return null;
    }

    @Override protected Object[] updateParameters(final CourseLecture object) {
        return new Object[0];
    }

    @Override protected String tableName() {
        return null;
    }

}
