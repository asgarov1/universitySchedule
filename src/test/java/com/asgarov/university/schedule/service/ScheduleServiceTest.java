package com.asgarov.university.schedule.service;

import com.asgarov.university.schedule.config.springConfiguration.SpringConfig;
import com.asgarov.university.schedule.domain.Course;
import com.asgarov.university.schedule.domain.Lecture;
import com.asgarov.university.schedule.domain.Professor;
import com.asgarov.university.schedule.domain.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { SpringConfig.class })
class ScheduleServiceTest {

    @Autowired
    StudentService studentService;

    @Autowired
    ProfessorService professorService;

    @Autowired
    CourseService courseService;

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    LectureService lectureService;

    @Autowired
    RoomService roomService;

    @Test
    void getAMonthScheduleForPerson() {
        Student student = studentService.findAll().get(0);
        Professor professor = professorService.findAll().get(0);

        assertNotNull(scheduleService.getAMonthScheduleForPerson(student));
        assertNotNull(scheduleService.getAMonthScheduleForPerson(professor));
    }

    @Test
    void getTodayScheduleForPerson() {
        Student student = studentService.findAll().get(0);

        List<Course> courses = courseService.findStudentsCourses(student);
        Course course = courses.get(0);
        Professor professor = professorService.findById(course.getProfessor().getId());

        Lecture lecture = new Lecture(LocalDateTime.now(), roomService.findAll().get(0));
        lecture.setId(lectureService.create(lecture));
        courseService.scheduleLectures(course, Collections.singletonList(lecture));

        assertNotNull(scheduleService.getTodayScheduleForPerson(student));
        assertNotNull(scheduleService.getTodayScheduleForPerson(professor));
    }
}
