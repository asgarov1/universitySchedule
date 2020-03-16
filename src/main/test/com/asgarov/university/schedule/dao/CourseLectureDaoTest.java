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
public class CourseLectureDaoTest {

    @Autowired
    CourseLectureDao courseLectureDao;

    @Autowired
    CourseDao courseDao;

    @Autowired
    LectureDao lectureDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    RoomDao roomDao;

    @Autowired CourseService courseService;

    @Test
    void createShouldWork() {
        int size = courseLectureDao.findAll().size();

        Course course = new Course("Biology");
        course.setProfessor(new Professor("Michael", "Michaelson"));
        Long courseId = courseDao.create(course);
        course.setId(courseId);

        courseService.registerStudents(course, studentDao.findAll().subList(0, 3));
        Long lectureId = lectureDao.create(new Lecture(LocalDateTime.now(), roomDao.findAll().get(0)));
        courseService.scheduleLectures(course, Collections.singletonList(lectureDao.findById(lectureId)));

        assertEquals(size + 1, courseLectureDao.findAll().size());
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
        List<CourseLecture> courseLectureList = courseLectureDao.findAll();
        CourseLecture expected = courseLectureList.get(0);
        CourseLecture actual = courseLectureDao.findById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    void findAllShouldWork() {
        List<CourseLecture> courseLectureList = courseLectureDao.findAll();
        assertNotNull(courseLectureList);
    }

    @Test
    void deleteByIdShouldWork() throws DaoException {
        List<CourseLecture> courseLectureList = courseLectureDao.findAll();

        Long id = courseLectureList.get(0).getId();
        courseLectureDao.deleteById(id);

        int expectedSize = courseLectureList.size() - 1;
        assertEquals(expectedSize, courseLectureDao.findAll().size());
    }
}
