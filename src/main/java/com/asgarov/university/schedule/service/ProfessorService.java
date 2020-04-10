package com.asgarov.university.schedule.service;

import com.asgarov.university.schedule.dao.ProfessorDao;
import com.asgarov.university.schedule.domain.Professor;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService extends AbstractDaoService<Long, Professor> {
    public ProfessorService(final ProfessorDao professorDao) {
        super(professorDao);
    }
}
