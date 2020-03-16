package com.asgarov.university.schedule.service;

import java.util.List;

import com.asgarov.university.schedule.dao.CourseLectureDao;
import com.asgarov.university.schedule.dao.CourseStudentDao;
import com.asgarov.university.schedule.domain.Course;
import com.asgarov.university.schedule.domain.CourseLecture;
import com.asgarov.university.schedule.domain.CourseStudent;
import com.asgarov.university.schedule.domain.Lecture;
import com.asgarov.university.schedule.domain.Student;

import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private CourseLectureDao courseLectureDao;
    private CourseStudentDao courseStudentDao;

    public CourseService(
            final CourseLectureDao courseLectureDao,
            final CourseStudentDao courseStudentDao) {
        this.courseLectureDao = courseLectureDao;
        this.courseStudentDao = courseStudentDao;
    }

    public void registerStudents(Course course, List<Student> students) {
        students.forEach(student -> {
            courseStudentDao.create(new CourseStudent(course.getId(), student.getId()));
            course.addStudent(student);
        });
    }

    public void scheduleLectures(Course course, List<Lecture> lectures) {
        lectures.forEach(lecture -> {
            courseLectureDao.create(new CourseLecture(course.getId(), lecture.getId()));
            course.addLecture(lecture);
        });
    }
}
