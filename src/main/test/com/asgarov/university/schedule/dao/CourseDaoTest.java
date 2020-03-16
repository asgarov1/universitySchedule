package com.asgarov.university.schedule.dao;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import com.asgarov.university.schedule.config.JDBCConfig;
import com.asgarov.university.schedule.dao.exception.DaoException;
import com.asgarov.university.schedule.domain.Course;
import com.asgarov.university.schedule.domain.Lecture;
import com.asgarov.university.schedule.service.CourseService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { JDBCConfig.class })
public class CourseDaoTest {

    @Autowired
    CourseDao courseDao;

    @Autowired
    ProfessorDao professorDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    LectureDao lectureDao;

    @Autowired
    RoomDao roomDao;

    @Autowired
    CourseService courseService;

    @Test
    void createShouldWork() {
        Course course = new Course("Biology");
        course.setProfessor(professorDao.findAll().get(0));
        Long courseId = courseDao.create(course);
        course.setId(courseId);

        courseService.registerStudents(course, studentDao.findAll().subList(0, 3));
        Long lectureId = lectureDao.create(new Lecture(LocalDateTime.now(), roomDao.findAll().get(0)));
        courseService.scheduleLectures(course, Collections.singletonList(lectureDao.findById(lectureId)));

        Course actual = courseDao.findById(courseId);


        Course expected = course;
        assertEquals(expected, actual);
    }

    @Test
    void updateShouldWork() throws DaoException {
        Course course = courseDao.findAll().get(0);
        course.setName("Docker 101");

        courseDao.update(course);

        Course actualCourse = courseDao.findById(course.getId());
        assertEquals(course, actualCourse);
    }

    @Test
    void findByIdShouldWork() {
        List<Course> courses = courseDao.findAll();
        Long courseId = courses.get(0).getId();

        Course expected = courses.get(0);
        Course actual = courseDao.findById(courseId);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void findAllShouldWork() {
        List<Course> courses = courseDao.findAll();
        assertNotNull(courses);
    }

    @Test
    void deleteByIdShouldWork() throws DaoException {
        List<Course> courses = courseDao.findAll();

        Long courseId = courses.get(0).getId();
        courseDao.deleteById(courseId);

        int expectedSize = courses.size() - 1;
        assertEquals(expectedSize, courseDao.findAll().size());
    }
}
