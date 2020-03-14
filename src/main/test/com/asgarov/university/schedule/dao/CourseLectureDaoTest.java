package com.asgarov.university.schedule.dao;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.asgarov.university.schedule.config.JDBCConfig;
import com.asgarov.university.schedule.dao.exception.DaoException;
import com.asgarov.university.schedule.domain.Course;
import com.asgarov.university.schedule.domain.CourseLecture;
import com.asgarov.university.schedule.domain.Lecture;
import com.asgarov.university.schedule.domain.Professor;
import com.asgarov.university.schedule.domain.Room;
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
public class CourseLectureDaoTest {

    @Autowired
    CourseLectureDao courseLectureDao;

    @Autowired
    CourseDao courseDao;

    @Autowired
    LectureDao lectureDao;

    @Test
    void createShouldWork() throws DaoException {
        int size = courseLectureDao.findAll().size();

        Course course = new Course("Biology");
        course.setProfessor(new Professor("Michael", "Michaelson"));
        course.setRegisteredStudents(Arrays.asList(new Student("Johnny", "Depp", Student.Degree.DOCTORATE),
                new Student("Angelina", "Jolia", Student.Degree.MASTER)));
        course.setLectures(Collections.singletonList(new Lecture(LocalDateTime.now(), new Room("A322"))));
        Long courseId = courseDao.create(course);

        assertEquals(size+1, courseLectureDao.findAll().size());
    }

    @Test
    void updateShouldWork() throws DaoException {
        CourseLecture courseLecture = courseLectureDao.findAll().get(0);
        courseLecture.setCourseId(courseLecture.getCourseId() + 1);

        courseLectureDao.update(courseLecture);

        CourseLecture actualCourseLecture = courseLectureDao.findById(courseLecture.getId());
        assertEquals(courseLecture, actualCourseLecture);
    }

    @Test
    void findByIdShouldWork() {
        assertNotNull(courseLectureDao.findById(1L));
    }

    @Test
    void findAllShouldWork() {
        List<CourseLecture> courses = courseLectureDao.findAll();
        assertNotNull(courses);
    }

    @Test
    void deleteByIdShouldWork() throws DaoException {
        List<CourseLecture> courses = courseLectureDao.findAll();

        Long id = courses.get(0).getId();
        courseLectureDao.deleteById(id);

        int expectedSize = courses.size() - 1;
        assertEquals(expectedSize, courseLectureDao.findAll().size());
    }
}
