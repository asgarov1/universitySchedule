package com.asgarov.university.schedule.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Lecture {

    private Long id;

    private LocalDateTime dateTime;

    private Room location;

    public Lecture() {
    }

    public Lecture(LocalDateTime dateTime, Room location) {
        this.dateTime = dateTime;
        this.location = location;
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

    @Override public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        final Lecture lecture = (Lecture) o;

        if (!Objects.equals(id, lecture.id))
            return false;
        if (!Objects.equals(dateTime, lecture.dateTime))
            return false;
        return Objects.equals(location, lecture.location);
    }

    @Override public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }

    @Override public String toString() {
        return "Lecture{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", location=" + location +
                '}';
    }
}
