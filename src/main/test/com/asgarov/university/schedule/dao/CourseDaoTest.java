package com.asgarov.university.schedule.dao;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.asgarov.university.schedule.config.JDBCConfig;
import com.asgarov.university.schedule.dao.exception.DaoException;
import com.asgarov.university.schedule.domain.Course;
import com.asgarov.university.schedule.domain.Lecture;
import com.asgarov.university.schedule.domain.Professor;
import com.asgarov.university.schedule.domain.Room;
import com.asgarov.university.schedule.domain.Student;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { JDBCConfig.class })
public class CourseDaoTest {

    @Autowired
    CourseDao courseDao;

    @Test
    void createCourseShouldWork() throws DaoException {
        Course course = new Course("Biology");
        course.setProfessor(new Professor("Michael", "Michaelson"));
        course.setRegisteredStudents(Arrays.asList(new Student("Johnny", "Depp", Student.Degree.DOCTORATE), new Student("Angelina", "Jolia", Student.Degree.MASTER)));
        course.setLectures(Collections.singletonList(new Lecture(LocalDateTime.now(), new Room("A322"))));

        Long courseId = courseDao.create(course);

        Course actual = courseDao.findById(courseId);
        assertEquals(course.getName(), actual.getName());
    }

    @Test
    void updateCourseShouldWork() throws DaoException {
        List<Course> courses = courseDao.findAll();
        Course course = courses.get(0);
        course.setName("Docker 101");

        courseDao.update(course);

        Course actualCourse = courseDao.findById(course.getId());
        assertEquals(course, actualCourse);
    }

    @Test
    void findByIdShouldWork() {
        assertNotNull(courseDao.findById(20L));
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

        int expectedSize = courses.size()-1;
        assertEquals(expectedSize, courseDao.findAll().size());
    }
}
