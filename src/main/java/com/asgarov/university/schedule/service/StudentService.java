package com.asgarov.university.schedule.service;

import com.asgarov.university.schedule.dao.StudentDao;
import com.asgarov.university.schedule.domain.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends AbstractDaoService<Long, Student> {

    private final StudentDao studentDao;

    public StudentService(final StudentDao studentDao) {
        super(studentDao);
        this.studentDao = studentDao;
    }
}
