package com.asgarov.university.schedule.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.asgarov.university.schedule.dao.exception.DaoException;
import com.asgarov.university.schedule.domain.Student;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StudentServiceTest {

    StudentService studentService = Mockito.mock(StudentService.class);

    @Test
    void createShouldWork() {
        Student student = new Student("John", "Maximilianov", Student.Degree.MASTER);
        when(studentService.create(student)).thenReturn(1L);
        Long studentId = studentService.create(student);
        student.setId(studentId);

        when(studentService.findById(studentId)).thenReturn(student);
        Student actual = studentService.findById(studentId);
        assertEquals(student, actual);
        verify(studentService, times(1)).findById(studentId);
    }

    @Test
    void updateShouldWork() throws DaoException {
        Student mockStudent = new Student("Mark", "Zukerberg", Student.Degree.DOCTORATE);
        mockStudent.setId(1L);
        when(studentService.findById(1L)).thenReturn(mockStudent);
        Student student = studentService.findById(1L);
        student.setFirstName("Sergey");
        student.setLastName("Nemchinskiy");

        studentService.update(student);

        Student actualStudent = studentService.findById(student.getId());
        assertEquals(student, actualStudent);
    }

    @Test
    void findAllShouldWork() {
        Student mockStudent = new Student("Mark", "Zukerberg", Student.Degree.DOCTORATE);
        when(studentService.findAll()).thenReturn(Collections.singletonList(mockStudent));

        List<Student> students = studentService.findAll();
        assertNotNull(students);
    }

    @Test
    void findByIdShouldWork() {
        Student mockStudent = new Student("Mark", "Zukerberg", Student.Degree.DOCTORATE);
        mockStudent.setId(1L);
        when(studentService.findAll()).thenReturn(Collections.singletonList(mockStudent));

        List<Student> students = studentService.findAll();
        Student expected = students.get(0);

        when(studentService.findById(1L)).thenReturn(mockStudent);
        Student actual = studentService.findById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    void deleteByIdShouldWork() throws DaoException {
        Student student = new Student("John", "Maximilianov", Student.Degree.MASTER);
        List<Student> students = new ArrayList<>();

        doAnswer(iom -> {
            students.add(student);
            return 1L;
        }).when(studentService).create(student);
        Long studentId = studentService.create(student);

        when(studentService.findAll()).thenReturn(students);
        int sizeBeforeDelete = studentService.findAll().size();

        doAnswer(iom -> students.remove(student)).when(studentService).deleteById(studentId);
        studentService.deleteById(studentId);

        int expectedSize = sizeBeforeDelete - 1;
        assertEquals(expectedSize, studentService.findAll().size());
    }
}
