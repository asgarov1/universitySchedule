package com.asgarov.university.schedule.service;

import com.asgarov.university.schedule.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    private CourseService courseService;

    public ScheduleService(final CourseService courseService) {
        this.courseService = courseService;
    }

    public List<DaySchedule> getAMonthScheduleForPerson(Person person) {
        List<Course> courses = new ArrayList<>();
        if(person instanceof Student) {
            Student student = (Student) person;
            courses = courseService.findStudentsCourses(student);
        } else if (person instanceof Professor) {
            Professor professor = (Professor) person;
            courses = courseService.findProfessorsCourses(professor);
        }

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
