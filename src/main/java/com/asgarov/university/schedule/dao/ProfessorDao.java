package com.asgarov.university.schedule.dao;

import com.asgarov.university.schedule.domain.Professor;
import com.asgarov.university.schedule.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class ProfessorDao extends AbstractDao<Long, Professor> {

    @Override
    public Professor findById(Long id) {
        try(Session session = HibernateUtil.getHibernateSession()){
            return session.get(Professor.class, id);
        }
    }

    @Override
    protected String from() {
        return "from Professor";
    }
}
