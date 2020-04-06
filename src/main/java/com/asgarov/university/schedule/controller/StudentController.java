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

    @GetMapping("/searchStudentsById")
    public String searchStudentsById(@RequestParam Long id, Model model) {
        model.addAttribute("students", Collections.singletonList(studentService.findById(id)));
        return "student";
    }

    @PostMapping
    public String addNew(Student student, @RequestParam String degree, Model model) {
        student.setDegree(Degree.valueOf(degree));
        studentService.create(student);
        model.addAttribute("students", studentService.findAll());
        return "student";
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id, Model model) throws DaoException {
        studentService.deleteById(id);
        return "redirect:/student";
    }

    @PutMapping("/{id}")
    public String updateStudent(@PathVariable Long id, Student student, @RequestParam String degree) throws DaoException {
        student.setId(id);
        student.setDegree(Degree.valueOf(degree));
        studentService.update(student);
        return "redirect:/student";
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
