package com.asgarov.university.schedule.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.asgarov.university.schedule.domain.Course;
import com.asgarov.university.schedule.domain.DaySchedule;
import com.asgarov.university.schedule.domain.Lecture;
import com.asgarov.university.schedule.domain.Person;

import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    private CourseService courseService;

    public ScheduleService(final CourseService courseService) {
        this.courseService = courseService;
    }

    public List<DaySchedule> getAMonthScheduleForPerson(Person person) {
        List<Course> courses = person.getRegisteredCourses(courseService);

        Map<LocalDate, List<Lecture>> schedules = courses.stream()
                .map(Course::getLectures)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Lecture::getDateTime))
                .collect(Collectors.groupingBy(lecture -> lecture.getDateTime().toLocalDate()));

        List<DaySchedule> daySchedules = new ArrayList<>();
        schedules.forEach((date, lecture) -> {
            daySchedules.add(new DaySchedule(date, lecture));
        });

        daySchedules.sort(Comparator.comparing(DaySchedule::getLocalDate));

        return daySchedules;
    }

    public DaySchedule getTodayScheduleForPerson(final Person person) {
        List<DaySchedule> daySchedules = getAMonthScheduleForPerson(person);
        return daySchedules.stream()
                .filter(daySchedule -> daySchedule.getLocalDate().equals(LocalDate.now()))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}