package com.asgarov.university.schedule.service;

import com.asgarov.university.schedule.dao.LectureDao;
import com.asgarov.university.schedule.domain.Lecture;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureService extends AbstractDaoService<Long, Lecture> {

    private LectureDao lectureDao;

    public LectureService(final LectureDao lectureDao) {
        super(lectureDao);
        this.lectureDao = lectureDao;
    }

    public List<Lecture> findAmount(int amount) {
        return lectureDao.findAmount(amount);
    }
}
