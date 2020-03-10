package com.asgarov.university.schedule.domain;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lecture lecture = (Lecture) o;

        if (!Objects.equals(dateTime, lecture.dateTime)) return false;
        if (!Objects.equals(location, lecture.location)) return false;
        return Objects.equals(course, lecture.course);
    }

    @Override
    public int hashCode() {
        int result = dateTime != null ? dateTime.hashCode() : 0;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (course != null ? course.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "dateTime=" + dateTime +
                ", location=" + location +
                ", course=" + course +
                '}';
    }
}
