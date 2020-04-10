package com.asgarov.university.schedule.service;

import com.asgarov.university.schedule.config.WebConfig;
import com.asgarov.university.schedule.domain.Course;
import com.asgarov.university.schedule.domain.Lecture;
import com.asgarov.university.schedule.domain.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.asgarov.university.schedule.domain.Student.Degree.DOCTORATE;
import static com.asgarov.university.schedule.domain.Student.Degree.MASTER;
import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = { WebConfig.class })
@ExtendWith(SpringExtension.class)
class CourseServiceTest {
    
    @Autowired
    CourseService courseService;

    @Autowired
    StudentService studentService;

    @Autowired
    LectureService lectureService;

    @Autowired
    RoomService roomService;

    @Autowired
    ProfessorService professorService;

    @Test
    void registerStudents() {
        Student depp = new Student("Johnny", "Depp", DOCTORATE);
        studentService.create(depp);

        Student jolie = new Student("Angelina", "Jolie", MASTER);
        studentService.create(jolie);

        Course course = new Course("Acting Classes for over 50");
        courseService.create(course);

        courseService.registerStudents(course, Arrays.asList(depp, jolie));

        List<Student> deppAndJolie = Arrays.asList(depp, jolie);
        List<Student> registeredStudents = courseService.findById(course.getId()).getRegisteredStudents();
        deppAndJolie.forEach(
                student -> assertTrue(registeredStudents.contains(student)));
    }

    @Test
    void scheduleLectures() {
        Lecture lecture = new Lecture(LocalDateTime.now(), roomService.findAll().get(0));
        lectureService.create(lecture);

        Course course = courseService.findAll().get(0);
        course.addLecture(lecture);
        courseService.update(course);

        List<Lecture> lecturesForTheCourse = courseService.findById(course.getId()).getLectures();
        assertTrue(lecturesForTheCourse.contains(lecture));
    }

    @Test
    void createShouldWork() {
        Course course = new Course("Biology");

        courseService.create(course);
        Course actual = courseService.findById(course.getId());

        assertEquals(course, actual);
    }

    @Test
    void updateShouldWork() {
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
    void deleteByIdShouldWork() {
        List<Course> courses = courseService.findAll();

        Long courseId = courses.get(0).getId();
        courseService.deleteById(courseId);

        int expectedSize = courses.size() - 1;
        assertEquals(expectedSize, courseService.findAll().size());
    }
}
