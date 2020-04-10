package com.asgarov.university.schedule.service;

import com.asgarov.university.schedule.dao.AbstractDao;
import com.asgarov.university.schedule.domain.Course;
import com.asgarov.university.schedule.domain.Lecture;
import com.asgarov.university.schedule.domain.Professor;
import com.asgarov.university.schedule.domain.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CourseService extends AbstractDaoService<Long, Course> {

    private final StudentService studentService;
    private final LectureService lectureService;

    public CourseService(AbstractDao<Long, Course> abstractDao, StudentService studentService, LectureService lectureService) {
        super(abstractDao);
        this.studentService = studentService;
        this.lectureService = lectureService;
    }

    public void registerStudents(Course course, List<Student> students) {
       students.forEach(course::addStudent);
       update(course);
    }

    public List<Course> findStudentsCourses(final Student student) {
        return findAll().stream()
                .filter(course -> studentHasCourse(student, course))
                .collect(Collectors.toList());
    }


    public List<Course> findProfessorsCourses(final Professor professor) {
        return findAll().stream()
                .filter(course -> Objects.equals(course.getProfessor(), professor))
                .collect(Collectors.toList());
    }

    public boolean studentHasCourse(Student student, Course course) {
        return course.getRegisteredStudents().contains(student);
    }

    public Course findCourseByLectureId(Long lectureId) {
        Lecture lecture = lectureService.findById(lectureId);

        return findAll()
                .stream()
                .filter(course -> course.getLectures().contains(lecture))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    public Course findCourseByLectureId(Integer lectureId) {
        return findCourseByLectureId((long) lectureId);
    }

    public Course findCourseByLectureId(String lectureId) {
        return findCourseByLectureId(Long.valueOf(lectureId));
    }

    public List<Student> getNotRegisteredStudents(Course course) {
        List<Student> notRegisteredStudents = studentService.findAll();
        notRegisteredStudents.removeAll(course.getRegisteredStudents());
        return notRegisteredStudents;
    }

    public void scheduleLectures(Course course, List<Lecture> lectures) {
        for (Lecture lecture : lectures) {
            course.addLecture(lecture);
        }
        update(course);
    }

    public void registerStudent(Long courseId, Long studentId) {
        Course course = findById(courseId);
        course.addStudent(studentService.findById(studentId));
        update(course);
    }

    public void scheduleLecture(Long courseId, Long lectureId) {
        Course course = findById(courseId);
        course.addLecture(lectureService.findById(lectureId));
    }

    public void unregisterStudent(Long courseId, Long studentId) {
        Course course = findById(courseId);
        course.removeStudent(studentService.findById(studentId));
    }

    public void removeLecture(Long courseId, Long lectureId) {
        Course course = findById(courseId);
        course.removeLecture(lectureService.findById(lectureId));
    }
}
