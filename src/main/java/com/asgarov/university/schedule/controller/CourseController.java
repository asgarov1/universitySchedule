package com.asgarov.university.schedule.controller;

import com.asgarov.university.schedule.dao.exception.DaoException;
import com.asgarov.university.schedule.domain.Course;
import com.asgarov.university.schedule.domain.Professor;
import com.asgarov.university.schedule.domain.dto.CourseDTO;
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

    @PostMapping("/{id}/registerStudent")
    public String registerStudent(@PathVariable Long id, @RequestParam Long studentId, Model model) {
        courseService.registerStudent(id, studentId);
        return "redirect:/course/" + id + "/students";
    }

    @GetMapping("/searchAll")
    public String searchAll(Model model) {
        model.addAttribute("courses", courseService.findAll());
        return "course";
    }

    @GetMapping("/{id}/delete")
    public String deleteCourse(@PathVariable Long id, Course course, Model model) throws DaoException {
        courseService.deleteById(id);
        model.addAttribute("courses", courseService.findAll());
        return "course";
    }

    @GetMapping("/{id}/removeStudent/{studentId}")
    public String removeStudentFromCourse(@PathVariable Long id, @PathVariable Long studentId, Model model) throws DaoException {
        Course course = courseService.findById(id);
        courseService.unregisterStudent(course, studentId);
        return "redirect:/course/" + id + "/students";
    }

    @RequestMapping("/{id}/update")
    public String updateCourse(@PathVariable Long id, CourseDTO courseDTO, Model model) throws DaoException {
        Course course = courseService.findById(id);
        courseService.update(course, courseDTO);
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
        model.addAttribute("notRegisteredStudents", courseService.getNotRegisteredStudents(course));
        return "courseStudents";
    }

    @ModelAttribute("courseService")
    public CourseService courseService() {
        return courseService;
    }

    @ModelAttribute("newCourse")
    public CourseDTO newCourseDTO() {
        return new CourseDTO();
    }

    @ModelAttribute("professors")
    public List<Professor> professors() {
        return professorService.findAll();
    }

}
