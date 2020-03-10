package com.asgarov.university.schedule.domain;

import java.time.LocalDateTime;

public class Lecture {

    private LocalDateTime dateTime;
    private Room location;
    private Course course;

    public Lecture() {
    }

    public Lecture(LocalDateTime dateTime, Room location, Course course) {
        this.dateTime = dateTime;
        this.location = location;
        this.course = course;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Room getLocation() {
        return location;
    }

    public void setLocation(Room location) {
        this.location = location;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
