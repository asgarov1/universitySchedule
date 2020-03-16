package com.asgarov.university.schedule.service;

import com.asgarov.university.schedule.dao.RoomDao;
import com.asgarov.university.schedule.domain.Room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService extends AbstractDaoService<Long, Room> {

    @Autowired
    public RoomService(final RoomDao roomDao) {
        super(roomDao);
    }
}
