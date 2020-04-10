package com.asgarov.university.schedule.dao;

import com.asgarov.university.schedule.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public abstract class AbstractDao<K, T> {

    public Long create(T object) {
        Long id;
        try (Session session = HibernateUtil.getHibernateSession()) {
            session.beginTransaction();
            id = (Long) session.save(object);
            session.getTransaction().commit();
        }
        return id;
    }

    public abstract T findById(K id);

    public void update(T object) {
        try (Session session = HibernateUtil.getHibernateSession()) {
            session.beginTransaction();
            session.update(object);
            session.getTransaction().commit();
        }
    }

    public void deleteById(K id) {
        try (Session session = HibernateUtil.getHibernateSession()) {
            session.beginTransaction();
            session.createQuery(getDeleteQuery(id)).executeUpdate();
            session.getTransaction().commit();
        }
    }

    private String getDeleteQuery(K id) {
        return "delete " + from() + " where id=" + id;
    }

    public List<T> findAll() {
        List<T> objects;
        try (Session session = HibernateUtil.getHibernateSession()) {
            session.beginTransaction();
            objects = session.createQuery(from()).getResultList();
            session.getTransaction().commit();
        }
        return objects;
    }

    protected abstract String from();
}
