package com.asgarov.university.schedule.controller;

import com.asgarov.university.schedule.dao.exception.DaoException;
import com.asgarov.university.schedule.domain.Course;
import com.asgarov.university.schedule.domain.Professor;
import com.asgarov.university.schedule.service.CourseService;
import com.asgarov.university.schedule.service.ProfessorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("course")
public class CourseController {
    private CourseService courseService;
    private ProfessorService professorService;

    public CourseController(CourseService courseService, ProfessorService professorService) {
        this.courseService = courseService;
        this.professorService = professorService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("courses", courseService.findAll());
        return "course";
    }

    @PostMapping("/searchCoursesById")
    public String searchUsersById(@RequestParam Long id, Model model) {
        model.addAttribute("courses", Collections.singletonList(courseService.findById(id)));
        return "course";
    }

    @GetMapping("/searchAll")
    public String searchAll(Model model) {
        model.addAttribute("courses", courseService.findAll());
        return "course";
    }

    @GetMapping("/{id}/delete")
    public String deleteCourse(@PathVariable Long id, Model model) throws DaoException {
        courseService.deleteById(id);
        model.addAttribute("courses", courseService.findAll());
        return "course";
    }


    @GetMapping("/{id}/lectures")
    public String showCourseLectures(@PathVariable Long id, Model model) {
        Course course = courseService.findById(id);
        model.addAttribute("course", course);
        model.addAttribute("lectures", course.getLectures());
        return "courseLectures";
    }

    @GetMapping("/{id}/students")
    public String showCourseStudents(@PathVariable Long id, Model model) {
        Course course = courseService.findById(id);
        model.addAttribute("course", course);
        model.addAttribute("students", course.getRegisteredStudents());
        return "courseStudents";
    }

    @ModelAttribute("courseService")
    public CourseService courseService() {
        return courseService;
    }

    @ModelAttribute("newCourse")
    public Course newCourse() {
        return new Course();
    }

    @ModelAttribute("professors")
    public List<Professor> professors() {
        return professorService.findAll();
    }

}
