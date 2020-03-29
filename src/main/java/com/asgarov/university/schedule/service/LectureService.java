package com.asgarov.university.schedule.service;

import com.asgarov.university.schedule.dao.AbstractDao;
import com.asgarov.university.schedule.dao.exception.DaoException;
import com.asgarov.university.schedule.domain.Lecture;
import org.springframework.stereotype.Service;

@Service
public class LectureService extends AbstractDaoService<Long, Lecture> {

    private CourseService courseService;

    public LectureService(AbstractDao<Long, Lecture> abstractDao, CourseService courseService) {
        super(abstractDao);
        this.courseService = courseService;
    }

    @Override
    public void deleteById(Long id) throws DaoException {
        courseService.removeLecture(id);
        super.deleteById(id);
    }
}
