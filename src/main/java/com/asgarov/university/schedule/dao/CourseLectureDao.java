package com.asgarov.university.schedule.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.asgarov.university.schedule.domain.CourseLecture;

import org.springframework.stereotype.Component;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Component
public class CourseLectureDao extends AbstractDao<Long, CourseLecture> {
    public static void deleteByCourseId(final Long id) {
        throw new NotImplementedException();
    }

    public Long save(final CourseLecture courseLecture) {
        throw new NotImplementedException();
    }

    @Override protected String getUpdateQuery() {
        return "UPDATE " + tableName() + " SET course_id = ?, lecture_id = ? WHERE id = ?;";
    }

    @Override protected CourseLecture rowMapper(final ResultSet resultSet, final int rowNum) throws SQLException {
        CourseLecture courseLecture = new CourseLecture();
        courseLecture.setId(resultSet.getLong("id"));
        courseLecture.setCourseId(resultSet.getLong("course_id"));
        courseLecture.setLectureId(resultSet.getLong("lecture_id"));
        return courseLecture;
    }

    @Override protected Map<String, ?> parameters(final CourseLecture object) {
        return null;
    }

    @Override protected Object[] updateParameters(final CourseLecture courseLecture) {
        return new Object[] {
                courseLecture.getCourseId(), courseLecture.getLectureId(), courseLecture.getId()
        };
    }

    @Override protected String tableName() {
        return "Course_Lectures";
    }

}
