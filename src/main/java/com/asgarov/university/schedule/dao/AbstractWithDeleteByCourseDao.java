package com.asgarov.university.schedule.dao;

import com.asgarov.university.schedule.dao.exception.DaoException;

public abstract class AbstractWithDeleteByCourseDao<K, T> extends AbstractDao<K, T> {
    public void deleteByCourseId(final K id) throws DaoException {
        if (getJdbcTemplate().update(getDeleteByCourseQuery(), id) == 0) {
            throw new DaoException("Problem deleting entity");
        }
    }

    private String getDeleteByCourseQuery() {
        return "delete from " + tableName() + " where course_id = ?;";
    }
}
