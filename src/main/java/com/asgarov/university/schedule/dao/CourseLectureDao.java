package com.asgarov.university.schedule.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.asgarov.university.schedule.domain.CourseLectures;

import org.springframework.stereotype.Component;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Component
public class CourseLectureDao extends AbstractDao<Long, CourseLectures> {
    public static void deleteByCourseId(final Long id) {
        throw new NotImplementedException();
    }

    public Long save(final CourseLectures courseLecture) {
        throw new NotImplementedException();
    }

    @Override protected String getUpdateQuery() {
        return null;
    }

    @Override protected String getDeleteQuery() {
        return null;
    }

    @Override protected CourseLectures rowMapper(final ResultSet resultSet, final int rowNum) throws SQLException {
        CourseLectures courseLecture = new CourseLectures();
        courseLecture.setId(resultSet.getLong("id"));
        courseLecture.setCourseId(resultSet.getLong("course_id"));
        courseLecture.setLectureId(resultSet.getLong("lecture_id"));
        return courseLecture;
    }

    @Override protected Map<String, ?> parameters(final CourseLectures object) {
        return null;
    }

    @Override protected Object[] updateParameters(final CourseLectures object) {
        return new Object[0];
    }

    @Override protected String tableName() {
        return null;
    }

}
