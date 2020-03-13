package com.asgarov.university.schedule.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

public class DaySchedule {

    List<Lecture> lectures = new ArrayList<>();

    private LocalDate localDate;

    public DaySchedule() {
    }

    public DaySchedule(LocalDate localDate, List<Lecture> lectures) {
        this.localDate = localDate;
        this.lectures = lectures;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    @Override public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        final DaySchedule that = (DaySchedule) o;

        if (lectures != null ? !lectures.equals(that.lectures) : that.lectures != null)
            return false;
        return localDate != null ? localDate.equals(that.localDate) : that.localDate == null;
    }

    @Override public int hashCode() {
        int result = lectures != null ? lectures.hashCode() : 0;
        result = 31 * result + (localDate != null ? localDate.hashCode() : 0);
        return result;
    }

    @Override public String toString() {
        return "DaySchedule{" +
                "lectures=" + lectures +
                ", localDate=" + localDate +
                '}';
    }
}
