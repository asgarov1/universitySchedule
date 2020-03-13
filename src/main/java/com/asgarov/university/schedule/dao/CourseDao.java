package com.asgarov.university.schedule.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.asgarov.university.schedule.dao.exception.DaoException;
import com.asgarov.university.schedule.domain.Course;

import org.springframework.stereotype.Repository;

@Repository
public class CourseDao extends AbstractDao<Long, Course> {

    private StudentCourseDao studentCourseDao;
    private CourseLectureDao courseLectureDao;

    @Override protected String getUpdateQuery() {
        return "UPDATE " + tableName() + " SET name = ?, professor_id = ? WHERE id = ?;";
    }

    @Override protected Course rowMapper(final ResultSet resultSet, final int rowNum) throws SQLException {
        Course course = new Course();
        course.setId(resultSet.getLong("id"));
        course.setName(resultSet.getString("name"));

        String professorId = resultSet.getString("professor_id");
        //        course.setProfessor(professorDao.findById());
        //        course.setLectures(lectureDao.findAllByCourseId(course.getId()));
        //        course.setRegisteredStudents(studentCourseDao.findAllStudentsByCourseId());

        return course;
    }

    @Override
    protected Map<String, ?> parameters(Course course) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", course.getName());
        parameters.put("professor_id", 1L); //course.getProfessor().getId()
        parameters.put("id", course.getId());
        return parameters;
    }

    @Override protected String tableName() {
        return "Course";
    }

    @Override protected Object[] updateParameters(final Course course) {
        return new Object[] { course.getName(), course.getProfessor() == null ? 1L : course.getProfessor().getId(), course.getId() };
    }

    @Override public void deleteById(final Long id) throws DaoException {
//        CourseLectureDao.deleteByCourseId(id);  TODO implement
//        CourseStudentDao.deleteByCourseId(id);
        super.deleteById(id);
    }
}
