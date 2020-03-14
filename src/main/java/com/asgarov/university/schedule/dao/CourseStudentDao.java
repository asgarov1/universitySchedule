package com.asgarov.university.schedule.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.asgarov.university.schedule.dao.exception.DaoException;
import com.asgarov.university.schedule.domain.CourseStudent;

import org.springframework.stereotype.Repository;

@Repository
public class CourseStudentDao extends AbstractWithDeleteByCourseDao<Long, CourseStudent> {

    public void deleteByStudentId(final Long id) throws DaoException {
        if (getJdbcTemplate().update(getDeleteByStudentQuery(), id) == 0) {
            throw new DaoException("Problem deleting entity");
        }
    }

    private String getDeleteByStudentQuery() {
        return "delete from " + tableName() + " where student_id = ?;";
    }

    @Override protected String getUpdateQuery() {
        return "UPDATE " + tableName() + " SET course_id = ?, student_id = ? WHERE id = ?;";
    }

    @Override protected CourseStudent rowMapper(final ResultSet resultSet, final int rowNum) throws SQLException {
        CourseStudent courseStudent = new CourseStudent();
        courseStudent.setId(resultSet.getLong("id"));
        courseStudent.setCourseId(resultSet.getLong("course_id"));
        courseStudent.setStudentId(resultSet.getLong("student_id"));
        return courseStudent;
    }

    @Override protected Map<String, ?> parameters(final CourseStudent courseStudent) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", courseStudent.getId());
        parameters.put("course_id", courseStudent.getCourseId());
        parameters.put("student_id", courseStudent.getStudentId());
        return parameters;
    }

    @Override protected Object[] updateParameters(final CourseStudent courseStudent) {
        return new Object[] {courseStudent.getCourseId(), courseStudent.getStudentId(), courseStudent.getId() };
    }

    @Override protected String tableName() {
        return "Courses_Students";
    }
}
