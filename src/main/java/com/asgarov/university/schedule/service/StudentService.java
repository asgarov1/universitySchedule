package com.asgarov.university.schedule.service;

import com.asgarov.university.schedule.dao.StudentDao;
import com.asgarov.university.schedule.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService extends AbstractDaoService<Long, Student> {

    private StudentDao studentDao;

    @Autowired
    public StudentService(final StudentDao studentDao) {
        super(studentDao);
        this.studentDao = studentDao;
    }

    public List<Student> findAllStudentsByCourseId(final Long id) {
        return studentDao.findAllStudentsByCourseId(id);
    }
}
