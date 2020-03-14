package com.asgarov.university.schedule.dao;

import java.util.List;

import com.asgarov.university.schedule.config.JDBCConfig;
import com.asgarov.university.schedule.dao.exception.DaoException;
import com.asgarov.university.schedule.domain.Room;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { JDBCConfig.class })
public class RoomDaoTest {

    @Autowired
    RoomDao roomDao;

    @Test
    void createShouldWork() throws DaoException {
        Room room = new Room("A111");
        Long roomId = roomDao.create(room);

        Room actual = roomDao.findById(roomId);
        assertEquals(room.getName(), actual.getName());
    }

    @Test
    void updateShouldWork() throws DaoException {
        Room room = roomDao.findAll().get(0);
        room.setName("B999");

        roomDao.update(room);

        Room actualRoom = roomDao.findById(room.getId());
        assertEquals(room, actualRoom);
    }

    @Test
    void findByIdShouldWork() {
        assertNotNull(roomDao.findById(1L));
    }

    @Test
    void findAllShouldWork() {
        List<Room> rooms = roomDao.findAll();
        assertNotNull(rooms);
    }

    @Test
    void deleteByIdShouldWork() throws DaoException {
        Room room = new Room("C333");
        Long professorId = roomDao.create(room);

        int sizeBeforeDelete = roomDao.findAll().size();

        roomDao.deleteById(professorId);

        int expectedSize = sizeBeforeDelete-1;
        assertEquals(expectedSize, roomDao.findAll().size());
    }
}
