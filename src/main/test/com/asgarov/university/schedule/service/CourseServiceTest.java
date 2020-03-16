package com.asgarov.university.schedule.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.asgarov.university.schedule.config.JDBCConfig;
import com.asgarov.university.schedule.dao.CourseDao;
import com.asgarov.university.schedule.dao.LectureDao;
import com.asgarov.university.schedule.dao.RoomDao;
import com.asgarov.university.schedule.dao.StudentDao;
import com.asgarov.university.schedule.domain.Course;
import com.asgarov.university.schedule.domain.Lecture;
import com.asgarov.university.schedule.domain.Student;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.asgarov.university.schedule.domain.Student.Degree.DOCTORATE;
import static com.asgarov.university.schedule.domain.Student.Degree.MASTER;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { JDBCConfig.class })
class CourseServiceTest {

    @Autowired CourseService courseService;

    @Autowired StudentDao studentDao;

    @Autowired CourseDao courseDao;

    @Autowired LectureDao lectureDao;

    @Autowired RoomDao roomDao;

    @Test
    void registerStudents() {
        Student johnny = new Student("Johnny", "Depp", DOCTORATE);
        johnny.setId(studentDao.create(johnny));

        Student angelina = new Student("Angelina", "Jolie", MASTER);
        angelina.setId(studentDao.create(new Student("Angelina", "Jolie", MASTER)));

        Course course = courseDao.findAll().get(0);
        courseService.registerStudents(course, Arrays.asList(johnny, angelina));

        List<Student> deppAndJolie = Arrays.asList(johnny, angelina);
        List<Student> allStudentsInTheCourse = studentDao.findAllStudentsByCourseId(course.getId());
        deppAndJolie.forEach(
                student -> assertTrue(allStudentsInTheCourse.contains(student)));
    }

    @Test
    void scheduleLectures() {
        Lecture lecture = new Lecture(LocalDateTime.now(), roomDao.findAll().get(0));
        lecture.setId(lectureDao.create(lecture));

        Course course = courseDao.findAll().get(0);

        courseService.scheduleLectures(course, Collections.singletonList(lecture));
        assertTrue(lectureDao.findAllByCourseId(course.getId()).contains(lecture));
    }
}
