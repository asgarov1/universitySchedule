package com.asgarov.university.schedule.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.asgarov.university.schedule.dao.exception.DaoException;
import com.asgarov.university.schedule.domain.CourseLecture;

import org.springframework.stereotype.Component;

@Component
public class CourseLectureDao extends AbstractWithDeleteByCourseDao<Long, CourseLecture> {

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

    public void deleteByLectureId(final Long id) throws DaoException {
        if (getJdbcTemplate().update(getDeleteByLectureQuery(), id) == 0) {
            throw new DaoException("Problem deleting entity");
        }
    }

    private String getDeleteByLectureQuery() {
        return "delete from " + tableName() + " where lecture_id = ?;";
    }
}
