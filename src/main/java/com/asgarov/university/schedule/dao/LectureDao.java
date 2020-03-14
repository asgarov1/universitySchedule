package com.asgarov.university.schedule.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.asgarov.university.schedule.dao.exception.DaoException;
import com.asgarov.university.schedule.domain.Lecture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LectureDao extends AbstractDao<Long, Lecture> {

    private RoomDao roomDao;
    private CourseLectureDao courseLectureDao;

    @Autowired
    public void setRoomDao(final RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Autowired
    public void setCourseLectureDao(final CourseLectureDao courseLectureDao) {
        this.courseLectureDao = courseLectureDao;
    }

    @Override protected String getUpdateQuery() {
        return "UPDATE " + tableName() + " SET dateTime = ?, location_id = ? WHERE id = ?;";
    }

    @Override protected Lecture rowMapper(final ResultSet resultSet, final int rowNum) throws SQLException {
        Lecture lecture = new Lecture();
        lecture.setId(resultSet.getLong("id"));
        lecture.setDateTime(resultSet.getTimestamp("dateTime").toLocalDateTime());
        //        lecture.setLocation(roomDao.findById(resultSet.getLong("location_id")));
        return lecture;
    }

    @Override protected Map<String, ?> parameters(final Lecture lecture) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", lecture.getId());
        parameters.put("dateTime", lecture.getDateTime());
        parameters.put("location_id", lecture.getLocation().getId());
        return parameters;
    }

    @Override protected Object[] updateParameters(final Lecture lecture) {
        return new Object[] { lecture.getDateTime(), lecture.getLocation(), lecture.getId() };
    }

    @Override protected String tableName() {
        return "Lecture";
    }

    public void deleteById(Long id) throws DaoException {
        courseLectureDao.deleteByLectureId(id);
        super.deleteById(id);
    }
}