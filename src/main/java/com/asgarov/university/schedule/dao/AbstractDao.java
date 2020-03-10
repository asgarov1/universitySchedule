package com.asgarov.university.schedule.dao;

import javax.sql.DataSource;
import java.util.List;

public interface AbstractDao<PK, T> {

    public void setDataSource(DataSource dataSource);

    public boolean create(T object);

    public T findById(PK id);

    public List<T> findAll();

    public boolean delete(T object);

    public boolean update(T object);
}
