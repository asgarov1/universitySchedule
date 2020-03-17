package com.asgarov.university.schedule.service;

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
public class RoomServiceTest {

    @Autowired
    RoomService roomService;

    @Test
    void createShouldWork() {
        Room room = new Room("A111");
        Long roomId = roomService.create(room);
        room.setId(roomId);

        Room expected = room;
        Room actual = roomService.findById(roomId);
        assertEquals(expected, actual);
    }

    @Test
    void updateShouldWork() throws DaoException {
        Room room = roomService.findAll().get(0);
        room.setName("B999");

        roomService.update(room);

        Room actualRoom = roomService.findById(room.getId());
        assertEquals(room, actualRoom);
    }

    @Test
    void findAllShouldWork() {
        List<Room> rooms = roomService.findAll();
        assertNotNull(rooms);
    }

    @Test
    void findByIdShouldWork() {
        List<Room> rooms = roomService.findAll();
        Room expected = rooms.get(0);
        Room actual = roomService.findById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    void deleteByIdShouldWork() throws DaoException {
        Room room = new Room("C333");
        Long professorId = roomService.create(room);

        int sizeBeforeDelete = roomService.findAll().size();

        roomService.deleteById(professorId);

        int expectedSize = sizeBeforeDelete - 1;
        assertEquals(expectedSize, roomService.findAll().size());
    }
}