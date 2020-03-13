package com.asgarov.university.schedule.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CourseLecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long courseId;
    private Long lectureId;

    public CourseLecture() {
    }

    public CourseLecture(final Long courseId, final Long lectureId) {
        this.courseId = courseId;
        this.lectureId = lectureId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(final Long courseId) {
        this.courseId = courseId;
    }

    public Long getLectureId() {
        return lectureId;
    }

    public void setLectureId(final Long lectureId) {
        this.lectureId = lectureId;
    }
}
