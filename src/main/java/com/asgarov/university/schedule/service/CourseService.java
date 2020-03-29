package com.asgarov.university.schedule.service;

import com.asgarov.university.schedule.dao.AbstractDao;
import com.asgarov.university.schedule.dao.CourseLectureDao;
import com.asgarov.university.schedule.dao.CourseStudentDao;
import com.asgarov.university.schedule.dao.exception.DaoException;
import com.asgarov.university.schedule.domain.*;
import com.asgarov.university.schedule.domain.dto.CourseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService extends AbstractDaoService<Long, Course> {

    private CourseLectureDao courseLectureDao;
    private CourseStudentDao courseStudentDao;
    private ProfessorService professorService;
    private StudentService studentService;

    public CourseService(AbstractDao<Long, Course> abstractDao, CourseLectureDao courseLectureDao, CourseStudentDao courseStudentDao, ProfessorService professorService, StudentService studentService) {
        super(abstractDao);
        this.courseLectureDao = courseLectureDao;
        this.courseStudentDao = courseStudentDao;
        this.professorService = professorService;
        this.studentService = studentService;
    }

    public void registerStudents(Course course, List<Student> students) {
        students.forEach(student -> {
            courseStudentDao.create(new CourseStudent(course.getId(), student.getId()));
            course.addStudent(student);
        });
    }

    public void registerStudent(Long courseId, Long studentId) {
        courseStudentDao.create(new CourseStudent(courseId, studentId));
    }

    public void scheduleLectures(Course course, List<Lecture> lectures) {
        lectures.forEach(lecture -> {
            courseLectureDao.create(new CourseLecture(course.getId(), lecture.getId()));
            course.addLecture(lecture);
        });
    }

    public void scheduleLecture(Long id, Long lectureId) {
        courseLectureDao.create(new CourseLecture(id, lectureId));
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

    public Course findCourseByLectureId(Long lectureId) {
        return courseLectureDao.findByLectureId(lectureId)
                .stream()
                .map(cl -> findById(cl.getCourseId()))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    public Course findCourseByLectureId(Integer lectureId) {
        return findCourseByLectureId((long) lectureId);
    }

    public void update(Course course, CourseDTO courseDTO) throws DaoException {
        course.setName(courseDTO.getName());
        course.setProfessor(professorService.findById(courseDTO.getProfessorId()));
        update(course);
    }

    public void unregisterStudent(Course course, Long studentId) {
        courseStudentDao.deleteByStudentId(studentId);
    }

    public List<Student> getNotRegisteredStudents(Course course) {
        List<Student> notRegisteredStudents = studentService.findAll();
        notRegisteredStudents.removeAll(course.getRegisteredStudents());
        return notRegisteredStudents;
    }

    public void removeLecture(Long lectureId) {
        courseLectureDao.deleteByLectureId(lectureId);
    }
}
