package com.asgarov.university.schedule.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "lecture")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private LocalDateTime dateTime;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    private Room room;

    @ManyToOne
    private Course course;

    public Lecture() {
    }

    public Lecture(LocalDateTime dateTime, Room room) {
        this.dateTime = dateTime;
        this.room = room;
    }

    public Lecture(LocalDateTime dateTime, Room room, Course course) {
        this.dateTime = dateTime;
        this.room = room;
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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

        if (!Objects.equals(id, lecture.id)) return false;
        if (!Objects.equals(dateTime, lecture.dateTime)) return false;
        return Objects.equals(room, lecture.room);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (room != null ? room.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Lecture " + "at " + dateTime + " in " + room + "\n";
    }
}
