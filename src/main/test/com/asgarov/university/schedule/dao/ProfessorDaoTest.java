package com.asgarov.university.schedule.dao;

import java.util.List;

import com.asgarov.university.schedule.config.JDBCConfig;
import com.asgarov.university.schedule.dao.exception.DaoException;
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
    void createShouldWork() throws DaoException {
        Professor professor = new Professor("John", "Maximilianov");
        Long professorId = professorDao.create(professor);

        Professor actual = professorDao.findById(professorId);
        assertEquals(professor.getEmail(), actual.getEmail());
        assertEquals(professor.getFirstName(), actual.getFirstName());
        assertEquals(professor.getLastName(), actual.getLastName());
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
    void findByIdShouldWork() {
        assertNotNull(professorDao.findById(1L));
    }

    @Test
    void findAllShouldWork() {
        List<Professor> professors = professorDao.findAll();
        assertNotNull(professors);
    }

    @Test
    void deleteByIdShouldWork() throws DaoException {
        Professor professor = new Professor("John", "Wick");
        Long professorId = professorDao.create(professor);

        int sizeBeforeDelete = professorDao.findAll().size();

        professorDao.deleteById(professorId);

        int expectedSize = sizeBeforeDelete-1;
        assertEquals(expectedSize, professorDao.findAll().size());
    }
}
