package com.asgarov.university.schedule.service;

import com.asgarov.university.schedule.domain.Professor;
import com.asgarov.university.schedule.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService extends AbstractService<Professor, Long> {

    private ProfessorRepository professorRepository;

    public ProfessorService(final ProfessorRepository professorRepository) {
        super(professorRepository);
        this.professorRepository = professorRepository;
    }

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }
}
