package com.asgarov.university.schedule.service;

import com.asgarov.university.schedule.dao.RoomDao;
import com.asgarov.university.schedule.domain.Lecture;
import com.asgarov.university.schedule.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class RoomService extends AbstractDaoService<Long, Room> {

    public static final int LESSON_TIME_IN_MINUTES = 90;
    LectureService lectureService;

    @Autowired
    public RoomService(final RoomDao roomDao, final LectureService lectureService) {
        super(roomDao);
        this.lectureService = lectureService;
    }

    public boolean isRoomAvailable(Room room, LocalDateTime dateTime) {
        return lectureService.findAll()
                .stream()
                .noneMatch(lecture -> isLectureAtThatDateTime(lecture, room, dateTime));

    }

    private boolean isLectureAtThatDateTime(Lecture lecture, Room room, LocalDateTime dateTime) {
        if (!lecture.getRoom().equals(room)) {
            return false;
        }
        return Duration.between(lecture.getLectureTime(), dateTime).toMinutes() < LESSON_TIME_IN_MINUTES;
    }
}
