package com.asgarov.university.schedule.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.asgarov.university.schedule.domain.Room;

import org.springframework.stereotype.Component;

@Component
public class RoomDao extends AbstractDao<Long, Room> {
    @Override protected String getUpdateQuery() {
        return null;
    }

    @Override protected Room rowMapper(final ResultSet resultSet, final int rowNum) throws SQLException {
        return null;
    }

    @Override protected Map<String, ?> parameters(final Room object) {
        return null;
    }

    @Override protected Object[] updateParameters(final Room object) {
        return new Object[0];
    }

    @Override protected String tableName() {
        return null;
    }
}
