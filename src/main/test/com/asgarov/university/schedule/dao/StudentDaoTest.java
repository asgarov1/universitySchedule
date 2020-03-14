package com.asgarov.university.schedule.dao;

import java.util.List;

import com.asgarov.university.schedule.config.JDBCConfig;
import com.asgarov.university.schedule.dao.exception.DaoException;
import com.asgarov.university.schedule.domain.Professor;
import com.asgarov.university.schedule.domain.Student;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { JDBCConfig.class })
public class StudentDaoTest {

    @Autowired
    StudentDao studentDao;

    @Test
    void createShouldWork() throws DaoException {
        Student student = new Student("John", "Maximilianov", Student.Degree.MASTER);
        Long studentId = studentDao.create(student);

        Student actual = studentDao.findById(studentId);
        assertEquals(student.getEmail(), actual.getEmail());
        assertEquals(student.getFirstName(), actual.getFirstName());
        assertEquals(student.getLastName(), actual.getLastName());
    }

    @Test
    void updateShouldWork() throws DaoException {
        Student student = studentDao.findAll().get(0);
        student.setFirstName("Sergey");
        student.setLastName("Nemchinskiy");

        studentDao.update(student);

        Student actualStudent = studentDao.findById(student.getId());
        assertEquals(student, actualStudent);
    }

    @Test
    void findByIdShouldWork() {
        assertNotNull(studentDao.findById(1L));
    }

    @Test
    void findAllShouldWork() {
        List<Student> students = studentDao.findAll();
        assertNotNull(students);
    }

    @Test
    void deleteByIdShouldWork() throws DaoException {
        Student student = new Student("John", "Maximilianov", Student.Degree.MASTER);
        Long studentId = studentDao.create(student);

        int sizeBeforeDelete = studentDao.findAll().size();

        studentDao.deleteById(studentId);

        int expectedSize = sizeBeforeDelete-1;
        assertEquals(expectedSize, studentDao.findAll().size());
    }
}
