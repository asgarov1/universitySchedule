package com.asgarov.university.schedule.domain;

public class CourseLectures {

    private Long id;
    private Long courseId;
    private Long lectureId;

    public CourseLectures() {
    }

    public CourseLectures(final Long courseId, final Long lectureId) {
        this.courseId = courseId;
        this.lectureId = lectureId;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
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
