package com.asgarov.university.schedule.controller;

import com.asgarov.university.schedule.dao.exception.DaoException;
import com.asgarov.university.schedule.domain.Student;
import com.asgarov.university.schedule.domain.Student.Degree;
import com.asgarov.university.schedule.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@RequestMapping("student")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "student";
    }

    @PostMapping("/searchProfessorsById")
    public String searchStudentsById(@RequestParam Long id, Model model) {
        model.addAttribute("students", Collections.singletonList(studentService.findById(id)));
        return "student";
    }

    @GetMapping("/searchAll")
    public String searchAll(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "student";
    }

    @PostMapping("/addNew")
    public String addNew(Student student, @RequestParam String degree) {
        student.setDegree(Degree.valueOf(degree));
        studentService.create(student);
        return "redirect:/student/searchAll";
    }

    @GetMapping("deleteStudent/{id}")
    public String deleteStudent(@PathVariable Long id, Model model) throws DaoException {
        studentService.deleteById(id);
        return "redirect:/student/searchAll";
    }

    @PostMapping("/{id}/update")
    public String updateStudent(@PathVariable Long id, Student student, @RequestParam String degree) throws DaoException {
        student.setId(id);
        student.setDegree(Degree.valueOf(degree));
        studentService.update(student);
        return "redirect:/student/searchAll";
    }

    @ModelAttribute("newStudent")
    public Student newStudent() {
        return new Student();
    }

    @ModelAttribute("degrees")
    public Degree[] degree() {
        return Degree.values();
    }

}
