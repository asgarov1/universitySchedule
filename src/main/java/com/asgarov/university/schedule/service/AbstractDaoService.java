package com.asgarov.university.schedule.service;

import java.util.List;

import com.asgarov.university.schedule.dao.AbstractDao;
import com.asgarov.university.schedule.dao.exception.DaoException;

public abstract class AbstractDaoService<K, T> {

    private AbstractDao<K, T> abstractDao;

    public AbstractDaoService(final AbstractDao<K, T> abstractDao) {
        this.abstractDao = abstractDao;
    }

    public Long create(T object) {
        return abstractDao.create(object);
    }

    public T findById(K id) {
        return abstractDao.findById(id);
    }

    public List<T> findAll() {
        return abstractDao.findAll();
    }

    public void update(T object) throws DaoException {
        abstractDao.update(object);
    }

    public void deleteById(K id) throws DaoException {
        abstractDao.deleteById(id);
    }
}
