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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { JDBCConfig.class })
public class LectureDaoTest {

    @Autowired
    LectureDao lectureDao;

    @Test
    void createShouldWork() throws DaoException {
        Lecture lecture = new Lecture(LocalDateTime.now().plusDays(2), new Room("A333"));
        Long lectureId = lectureDao.create(lecture);

        Lecture actual = lectureDao.findById(lectureId);
        assertEquals(lecture.getDateTime(), actual.getDateTime());
//        assertEquals(lecture.getLocation(), actual.getLocation());
    }

    @Test
    void updateShouldWork() throws DaoException {
        List<Lecture> lectures = lectureDao.findAll();
        Lecture lecture = lectures.get(0);
        lecture.setDateTime(LocalDateTime.now().plusDays(1));

        lectureDao.update(lecture);

        Lecture actualLecture = lectureDao.findById(lecture.getId());
        assertEquals(lecture, actualLecture);
    }

    @Test
    void findByIdShouldWork() {
        assertNotNull(lectureDao.findById(20L));
    }

    @Test
    void findAllShouldWork() {
        List<Lecture> lectures = lectureDao.findAll();
        assertNotNull(lectures);
    }

    @Test
    void deleteByIdShouldWork() throws DaoException {
        List<Lecture> lectures = lectureDao.findAll();

        Long lectureId = lectures.get(0).getId();
        lectureDao.deleteById(lectureId);

        int expectedSize = lectures.size()-1;
        assertEquals(expectedSize, lectureDao.findAll().size());
    }
}
