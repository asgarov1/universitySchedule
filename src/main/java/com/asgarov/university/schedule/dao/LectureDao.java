package com.asgarov.university.schedule.dao;

import com.asgarov.university.schedule.domain.Lecture;
import com.asgarov.university.schedule.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class LectureDao extends AbstractDao<Long, Lecture> {

    @Override
    public Lecture findById(Long id) {
        try(Session session = HibernateUtil.getHibernateSession()){
            return session.get(Lecture.class, id);
        }
    }

    @Override
    protected String from() {
        return "from Lecture";
    }
}
