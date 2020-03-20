package com.asgarov.university.schedule.service;

import com.asgarov.university.schedule.dao.CourseDao;
import com.asgarov.university.schedule.dao.CourseLectureDao;
import com.asgarov.university.schedule.dao.CourseStudentDao;
import com.asgarov.university.schedule.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService extends AbstractDaoService<Long, Course> {
    private CourseLectureDao courseLectureDao;
    private CourseStudentDao courseStudentDao;

    public CourseService(
            final CourseLectureDao courseLectureDao,
            final CourseStudentDao courseStudentDao, final CourseDao courseDao) {
        super(courseDao);
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

    public List<Course> findStudentsCourses(final Student student) {
        return findAll().stream()
                .filter(course -> studentHasCourse(student, course))
                .collect(Collectors.toList());
    }


    public List<Course> findProfessorsCourses(final Professor professor) {
        return findAll().stream()
                .filter(course -> course.getProfessor().equals(professor))
                .collect(Collectors.toList());
    }

    public boolean studentHasCourse(Student student, Course course) {
        return course.getRegisteredStudents().contains(student);
    }

}
