package com.asgarov.university.schedule.service;

import com.asgarov.university.schedule.dao.LectureDao;
import com.asgarov.university.schedule.domain.Lecture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LectureService extends AbstractDaoService<Long, Lecture> {

    @Autowired
    public LectureService(final LectureDao lectureDao) {
        super(lectureDao);
    }
}
