package com.asgarov.university.schedule.controller;

import com.asgarov.university.schedule.domain.Course;
import com.asgarov.university.schedule.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CourseController {
    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("course/{id}/lectures")
    public String showCourseLectures(@PathVariable Long id, Model model) {
        Course course = courseService.findById(id);
        model.addAttribute("course", course);
        model.addAttribute("lectures", course.getLectures());
        return "courseLectures";
    }

    @ModelAttribute("courseService")
    public CourseService courseService() {
        return courseService;
    }
}
