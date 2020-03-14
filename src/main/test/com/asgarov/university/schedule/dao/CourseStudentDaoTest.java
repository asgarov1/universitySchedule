package com.asgarov.university.schedule.dao;

import java.util.List;

import com.asgarov.university.schedule.config.JDBCConfig;
import com.asgarov.university.schedule.dao.exception.DaoException;
import com.asgarov.university.schedule.domain.CourseStudent;
import com.asgarov.university.schedule.domain.Student;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { JDBCConfig.class })
public class CourseStudentDaoTest {

    @Autowired
    CourseStudentDao courseStudentDao;

    @Autowired
    CourseDao courseDao;


    @Autowired
    StudentDao studentDao;

    @Test
    void createShouldWork() throws DaoException {
        Long courseId = courseDao.findAll().get(0).getId();

        CourseStudent courseStudent = new CourseStudent();
        courseStudent.setCourseId(courseId);

        Student student = new Student("Javid", "Asgarov", Student.Degree.BACHELOR);
        Long studentId = studentDao.create(student);
        courseStudent.setStudentId(studentId);

        Long courseStudentId = courseStudentDao.create(courseStudent);
        assertNotNull(courseStudentDao.findById(courseStudentId));
    }

    @Test
    void updateShouldWork() throws DaoException {
        CourseStudent courseStudent = courseStudentDao.findAll().get(0);
        courseStudent.setCourseId(courseStudent.getCourseId()+1);

        courseStudentDao.update(courseStudent);

        CourseStudent actualCourseStudent = courseStudentDao.findById(courseStudent.getId());
        assertEquals(courseStudent, actualCourseStudent);
    }

    @Test
    void findByIdShouldWork() {
        assertNotNull(courseStudentDao.findById(13L));
    }

    @Test
    void findAllShouldWork() {
        List<CourseStudent> courseStudentList = courseStudentDao.findAll();
        assertNotNull(courseStudentList);
    }

    @Test
    void deleteByIdShouldWork() throws DaoException {
        List<CourseStudent> courseStudentList = courseStudentDao.findAll();

        Long courseId = courseStudentList.get(0).getId();
        courseStudentDao.deleteById(courseId);

        int expectedSize = courseStudentList.size()-1;
        assertEquals(expectedSize, courseStudentDao.findAll().size());
    }
}
