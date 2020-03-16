package com.asgarov.university.schedule.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.asgarov.university.schedule.dao.exception.DaoException;
import com.asgarov.university.schedule.domain.Course;

import org.springframework.stereotype.Repository;

@Repository
public class CourseDao extends AbstractDao<Long, Course> {

    private LectureDao lectureDao;
    private CourseStudentDao courseStudentDao;
    private CourseLectureDao courseLectureDao;
    private ProfessorDao professorDao;
    private StudentDao studentDao;

    public CourseDao(
            final LectureDao lectureDao,
            final CourseStudentDao courseStudentDao,
            final CourseLectureDao courseLectureDao,
            final ProfessorDao professorDao,
            final StudentDao studentDao) {
        this.lectureDao = lectureDao;
        this.courseStudentDao = courseStudentDao;
        this.courseLectureDao = courseLectureDao;
        this.professorDao = professorDao;
        this.studentDao = studentDao;
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE " + tableName() + " SET name = ?, professor_id = ? WHERE id = ?;";
    }

    @Override
    protected Course rowMapper(final ResultSet resultSet, final int rowNum) throws SQLException {
        Course course = new Course();
        course.setId(resultSet.getLong("id"));
        course.setName(resultSet.getString("name"));

        Long professorId = resultSet.getLong("professor_id");
        course.setProfessor(professorDao.findById(professorId));
        course.setLectures(lectureDao.findAllByCourseId(course.getId()));
        course.setRegisteredStudents(studentDao.findAllStudentsByCourseId(course.getId()));

        return course;
    }

    @Override
    protected Map<String, ?> createParameters(Course course) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", course.getName());
        parameters.put("professor_id", course.getProfessor().getId());
        parameters.put("id", course.getId());
        return parameters;
    }

    @Override
    protected String tableName() {
        return "course";
    }

    @Override
    protected Object[] updateParameters(final Course course) {
        return new Object[] { course.getName(), course.getProfessor().getId(), course.getId() };
    }

    @Override
    public void deleteById(final Long id) throws DaoException {
        courseLectureDao.deleteByCourseId(id);
        courseStudentDao.deleteByCourseId(id);
        super.deleteById(id);
    }
}
