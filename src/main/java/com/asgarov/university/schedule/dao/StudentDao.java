package com.asgarov.university.schedule.dao;

import com.asgarov.university.schedule.domain.Student;
import com.asgarov.university.schedule.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao extends AbstractDao<Long, Student> {
    @Override
    public Student findById(Long id) {
        Student student;
        try(Session session = HibernateUtil.getHibernateSession()){
            session.beginTransaction();
            student = session.get(Student.class, id);
            session.getTransaction().commit();
        }
        return student;
    }

    @Override
    protected String from() {
        return "from Student";
    }
}
