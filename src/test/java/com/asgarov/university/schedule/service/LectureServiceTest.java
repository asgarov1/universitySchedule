package com.asgarov.university.schedule.service;

import com.asgarov.university.schedule.config.JDBCConfig;
import com.asgarov.university.schedule.dao.exception.DaoException;
import com.asgarov.university.schedule.domain.Lecture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { JDBCConfig.class })
public class LectureServiceTest {

    @Autowired
    LectureService lectureService;

    @Autowired
    RoomService roomService;

    @Test
    void createShouldWork() {
        Lecture lecture = new Lecture(LocalDateTime.now().plusDays(2), roomService.findAll().get(0));
        Long lectureId = lectureService.create(lecture);
        lecture.setId(lectureId);

        Lecture actual = lectureService.findById(lectureId);
        assertEquals(lecture, actual);
    }

    @Test
    void updateShouldWork() throws DaoException {
        List<Lecture> lectures = lectureService.findAll();
        Lecture lecture = lectures.get(0);
        lecture.setDateTime(LocalDateTime.now().plusDays(1));

        lectureService.update(lecture);

        Lecture actualLecture = lectureService.findById(lecture.getId());
        assertEquals(lecture, actualLecture);
    }

    @Test
    void findByIdShouldWork() {
        List<Lecture> lectures = lectureService.findAll();
        Lecture expected = lectures.get(0);
        Lecture actual = lectureService.findById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    void findAllShouldWork() {
        List<Lecture> lectures = lectureService.findAll();
        assertNotNull(lectures);
    }

    @Test
    void deleteByIdShouldWork() throws DaoException {
        List<Lecture> lectures = lectureService.findAll();

        Long lectureId = lectures.get(0).getId();
        lectureService.deleteById(lectureId);

        int expectedSize = lectures.size() - 1;
        assertEquals(expectedSize, lectureService.findAll().size());
    }
}
