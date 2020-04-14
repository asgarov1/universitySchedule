package com.asgarov.university.schedule.dao;

import com.asgarov.university.schedule.domain.Course;
import com.asgarov.university.schedule.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDao extends AbstractDao<Long, Course> {

    @Override
    public Course findById(Long id) {
        try (Session session = HibernateUtil.getHibernateSession()) {
            return session.get(Course.class, id);
        }
    }

    @Override
    public List<Course> findAll() {
        List<Course> courses;
        try (Session session = HibernateUtil.getHibernateSession()) {
            session.beginTransaction();
            courses = session.createQuery(from(), Course.class).getResultList();
            session.getTransaction().commit();
        }
        return courses;
    }

    @Override
    protected String from() {
        return "from Course";
    }
}
