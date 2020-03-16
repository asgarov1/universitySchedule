package com.asgarov.university.schedule.service;

import java.util.List;

import com.asgarov.university.schedule.config.JDBCConfig;
import com.asgarov.university.schedule.dao.exception.DaoException;
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
public class StudentServiceTest {

    @Autowired
    StudentService studentService;

    @Test
    void createShouldWork() {
        Student student = new Student("John", "Maximilianov", Student.Degree.MASTER);
        Long studentId = studentService.create(student);
        student.setId(studentId);

        Student expected = student;
        Student actual = studentService.findById(studentId);

        assertEquals(expected, actual);
    }

    @Test
    void updateShouldWork() throws DaoException {
        Student student = studentService.findAll().get(0);
        student.setFirstName("Sergey");
        student.setLastName("Nemchinskiy");

        studentService.update(student);

        Student actualStudent = studentService.findById(student.getId());
        assertEquals(student, actualStudent);
    }

    @Test
    void findAllShouldWork() {
        List<Student> students = studentService.findAll();
        assertNotNull(students);
    }

    @Test
    void findByIdShouldWork() {
        List<Student> students = studentService.findAll();
        Student expected = students.get(0);
        Student actual = studentService.findById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    void deleteByIdShouldWork() throws DaoException {
        Student student = new Student("John", "Maximilianov", Student.Degree.MASTER);
        Long studentId = studentService.create(student);

        int sizeBeforeDelete = studentService.findAll().size();

        studentService.deleteById(studentId);

        int expectedSize = sizeBeforeDelete - 1;
        assertEquals(expectedSize, studentService.findAll().size());
    }
}
