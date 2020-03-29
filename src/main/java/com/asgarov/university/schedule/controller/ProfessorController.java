package com.asgarov.university.schedule.controller;

import com.asgarov.university.schedule.dao.exception.DaoException;
import com.asgarov.university.schedule.domain.Professor;
import com.asgarov.university.schedule.service.ProfessorService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@RequestMapping("professor")
public class ProfessorController {

    private ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("professors", professorService.findAll());
        return "professor";
    }

    @PostMapping("/searchProfessorsById")
    public String searchProfessorsById(@RequestParam Long id, Model model) {
        model.addAttribute("lectures", Collections.singletonList(professorService.findById(id)));
        return "professor";
    }

    @GetMapping("/searchAll")
    public String searchAll(Model model) {
        model.addAttribute("professors", professorService.findAll());
        return "professor";
    }

    @PostMapping("/addNew")
    public String addNew(Professor professor) {
        professorService.create(professor);
        return "redirect:/professor/searchAll";
    }

    @GetMapping("deleteProfessor/{id}")
    public String deleteProfessor(@PathVariable Long id, Model model) {
        try {
            professorService.deleteById(id);
        } catch (DataIntegrityViolationException | DaoException e) {
            model.addAttribute("error", "Can't delete, as this Professor is still registered for course(s)!");
            model.addAttribute("professors", professorService.findAll());
            return "professor";
        }
        return "redirect:/professor/searchAll";
    }

    @PostMapping("/{id}/update")
    public String updateProfessor(@PathVariable Long id, Professor professor) throws DaoException {
        professor.setId(id);
        professorService.update(professor);
        return "redirect:/professor/searchAll";
    }

    @ModelAttribute("newProfessor")
    public Professor newProfessor() {
        return new Professor();
    }
}
