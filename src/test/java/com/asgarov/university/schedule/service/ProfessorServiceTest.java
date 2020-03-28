package com.asgarov.university.schedule.service;

import com.asgarov.university.schedule.config.WebConfig;
import com.asgarov.university.schedule.dao.exception.DaoException;
import com.asgarov.university.schedule.domain.Course;
import com.asgarov.university.schedule.domain.Professor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { WebConfig.class })
public class ProfessorServiceTest {

    @Autowired
    ProfessorService professorService;

    @Autowired
    CourseService courseService;

    @Test
    void createShouldWork() {
        Professor professor = new Professor("John", "Maximilianov");
        Long professorId = professorService.create(professor);
        professor.setId(professorId);

        Professor expected = professor;
        Professor actual = professorService.findById(professorId);
        assertEquals(expected, actual);
    }

    @Test
    void updateShouldWork() throws DaoException {
        Professor professor = professorService.findAll().get(0);
        professor.setFirstName("Sergey");
        professor.setLastName("Nemchinskiy");

        professorService.update(professor);

        Professor actualProfessor = professorService.findById(professor.getId());
        assertEquals(professor, actualProfessor);
    }

    @Test
    void findAllShouldWork() {
        List<Professor> professors = professorService.findAll();
        assertNotNull(professors);
    }

    @Test
    void findByIdShouldWork() {
        List<Professor> professors = professorService.findAll();
        Professor expected = professors.get(0);
        Professor actual = professorService.findById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    void deleteByIdShouldWork() throws DaoException {
        Professor professor = new Professor("John", "Wick");
        Long professorId = professorService.create(professor);

        int sizeBeforeDelete = professorService.findAll().size();

        professorService.deleteById(professorId);

        int expectedSize = sizeBeforeDelete - 1;
        assertEquals(expectedSize, professorService.findAll().size());
    }

    @Test
    void getAllStudentsForCourseShouldWork() {
        Course course = courseService.findAll().get(0);
        assertNotNull(professorService.getAllStudentsForCourse(course));
    }
}
