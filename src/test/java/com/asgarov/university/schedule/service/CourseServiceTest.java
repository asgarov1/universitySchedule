package com.asgarov.university.schedule.service;

import com.asgarov.university.schedule.config.WebConfig;
import com.asgarov.university.schedule.dao.LectureDao;
import com.asgarov.university.schedule.dao.exception.DaoException;
import com.asgarov.university.schedule.domain.Course;
import com.asgarov.university.schedule.domain.Lecture;
import com.asgarov.university.schedule.domain.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.asgarov.university.schedule.domain.Student.Degree.DOCTORATE;
import static com.asgarov.university.schedule.domain.Student.Degree.MASTER;
import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = { WebConfig.class})
@ExtendWith(SpringExtension.class)
class CourseServiceTest {

    @Autowired
    CourseService courseService;

    @Autowired
    StudentService studentService;

    @Autowired
    LectureDao lectureDao;

    @Autowired
    RoomService roomService;

    @Autowired
    ProfessorService professorService;

    @Test
    void registerStudents() {
        Student depp = new Student("Johnny", "Depp", DOCTORATE);
        depp.setId(studentService.create(depp));

        Student jolie = new Student("Angelina", "Jolie", MASTER);
        jolie.setId(studentService.create(new Student("Angelina", "Jolie", MASTER)));

        Course course = courseService.findAll().get(0);
        courseService.registerStudents(course, Arrays.asList(depp, jolie));

        List<Student> deppAndJolie = Arrays.asList(depp, jolie);
        List<Student> allStudentsInTheCourse = studentService.findAllStudentsByCourseId(course.getId());
        deppAndJolie.forEach(
                student -> assertTrue(allStudentsInTheCourse.contains(student)));
    }

    @Test
    void scheduleLectures() {
        Lecture lecture = new Lecture(LocalDateTime.now(), roomService.findAll().get(0));
        lecture.setId(lectureDao.create(lecture));

        Course course = courseService.findAll().get(0);

        courseService.scheduleLectures(course, Collections.singletonList(lecture));
        assertTrue(lectureDao.findAllByCourseId(course.getId()).contains(lecture));
    }

    @Test
    void createShouldWork() {
        Course course = new Course("Biology");
        course.setProfessor(professorService.findAll().get(0));
        Long courseId = courseService.create(course);
        course.setId(courseId);

        courseService.registerStudents(course, studentService.findAll().subList(0, 3));
        Long lectureId = lectureDao.create(new Lecture(LocalDateTime.now(), roomService.findAll().get(0)));
        List<Lecture> lectures = new ArrayList<>();
        lectures.add(lectureDao.findById(lectureId));

        courseService.scheduleLectures(course, lectures);
        Course actual = courseService.findById(courseId);
        Course expected = course;
        assertEquals(expected, actual);
    }

    @Test
    void updateShouldWork() throws DaoException {
        Course course = courseService.findAll().get(0);
        course.setName("Docker 101");

        courseService.update(course);

        Course actualCourse = courseService.findById(course.getId());
        assertEquals(course, actualCourse);
    }

    @Test
    void findByIdShouldWork() {
        List<Course> courses = courseService.findAll();
        Long courseId = courses.get(0).getId();

        Course expected = courses.get(0);
        Course actual = courseService.findById(courseId);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void findAllShouldWork() {
        List<Course> courses = courseService.findAll();
        assertNotNull(courses);
    }

    @Test
    void deleteByIdShouldWork() throws DaoException {
        List<Course> courses = courseService.findAll();

        Long courseId = courses.get(0).getId();
        courseService.deleteById(courseId);

        int expectedSize = courses.size() - 1;
        assertEquals(expectedSize, courseService.findAll().size());
    }
}
