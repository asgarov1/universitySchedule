package com.asgarov.university.schedule.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DaySchedule {

    private LocalDate localDate;
    List<Lecture> lectures = new ArrayList<>();

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
}
