package com.asgarov.university.schedule.controller;

import com.asgarov.university.schedule.domain.*;
import com.asgarov.university.schedule.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {

    private CourseService courseService;
    private LectureService lectureService;
    private ProfessorService professorService;
    private RoomService roomService;
    private StudentService studentService;

    public HomeController(CourseService courseService, LectureService lectureService, ProfessorService professorService,
                          RoomService roomService, StudentService studentService) {
        this.courseService = courseService;
        this.lectureService = lectureService;
        this.professorService = professorService;
        this.roomService = roomService;
        this.studentService = studentService;
    }

    @GetMapping({"/", "", "/index"})
    public String home() {
        return "index";
    }

    @ModelAttribute("courses")
    public List<Course> allCourses() {
        return courseService.findAll();
    }

    @ModelAttribute("lectures")
    public List<Lecture> allLectures() {
        return lectureService.findAll();
    }

    @ModelAttribute("professors")
    public List<Professor> allProfessors() {
        return professorService.findAll();
    }

    @ModelAttribute("rooms")
    public List<Room> allRooms() {
        return roomService.findAll();
    }

    @ModelAttribute("students")
    public List<Student> allStudents() {
        return studentService.findAll();
    }

    @ModelAttribute("courseService")
    public CourseService courseService() {
        return courseService;
    }
}
