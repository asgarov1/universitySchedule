package com.asgarov.university.schedule.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.asgarov.university.schedule.config.JDBCConfig;
import com.asgarov.university.schedule.dao.exception.DaoException;
import com.asgarov.university.schedule.domain.CourseStudent;
import com.asgarov.university.schedule.domain.Lecture;
import com.asgarov.university.schedule.domain.Room;

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

    @Autowired
    RoomDao roomDao;

    @Test
    void createShouldWork() {
        Lecture lecture = new Lecture(LocalDateTime.now().plusDays(2), roomDao.findAll().get(0));
        Long lectureId = lectureDao.create(lecture);
        lecture.setId(lectureId);

        Lecture actual = lectureDao.findById(lectureId);
        assertEquals(lecture, actual);
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
        List<Lecture> lectures = lectureDao.findAll();
        Lecture expected = lectures.get(0);
        Lecture actual = lectureDao.findById(expected.getId());
        assertEquals(expected, actual);
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

        int expectedSize = lectures.size() - 1;
        assertEquals(expectedSize, lectureDao.findAll().size());
    }
}
