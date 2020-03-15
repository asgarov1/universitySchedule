package com.asgarov.university.schedule.dao;

import java.util.List;

import com.asgarov.university.schedule.config.JDBCConfig;
import com.asgarov.university.schedule.dao.exception.DaoException;
import com.asgarov.university.schedule.domain.Lecture;
import com.asgarov.university.schedule.domain.Professor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { JDBCConfig.class })
public class ProfessorDaoTest {

    @Autowired
    ProfessorDao professorDao;

    @Test
    void createShouldWork() {
        Professor professor = new Professor("John", "Maximilianov");
        Long professorId = professorDao.create(professor);
        professor.setId(professorId);

        Professor expected = professor;
        Professor actual = professorDao.findById(professorId);
        assertEquals(expected, actual);
    }

    @Test
    void updateShouldWork() throws DaoException {
        Professor professor = professorDao.findAll().get(0);
        professor.setFirstName("Sergey");
        professor.setLastName("Nemchinskiy");

        professorDao.update(professor);

        Professor actualProfessor = professorDao.findById(professor.getId());
        assertEquals(professor, actualProfessor);
    }

    @Test
    void findAllShouldWork() {
        List<Professor> professors = professorDao.findAll();
        assertNotNull(professors);
    }

    @Test
    void findByIdShouldWork() {
        List<Professor> professors = professorDao.findAll();
        Professor expected = professors.get(0);
        Professor actual = professorDao.findById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    void deleteByIdShouldWork() throws DaoException {
        Professor professor = new Professor("John", "Wick");
        Long professorId = professorDao.create(professor);

        int sizeBeforeDelete = professorDao.findAll().size();

        professorDao.deleteById(professorId);

        int expectedSize = sizeBeforeDelete - 1;
        assertEquals(expectedSize, professorDao.findAll().size());
    }
}
