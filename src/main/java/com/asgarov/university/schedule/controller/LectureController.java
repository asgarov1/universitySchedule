package com.asgarov.university.schedule.controller;

import com.asgarov.university.schedule.dao.exception.DaoException;
import com.asgarov.university.schedule.domain.Course;
import com.asgarov.university.schedule.domain.Lecture;
import com.asgarov.university.schedule.domain.Room;
import com.asgarov.university.schedule.service.CourseService;
import com.asgarov.university.schedule.service.LectureService;
import com.asgarov.university.schedule.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("lecture")
public class LectureController {

    private LectureService lectureService;
    private RoomService roomService;
    private CourseService courseService;

    public LectureController(LectureService lectureService, RoomService roomService, CourseService courseService) {
        this.lectureService = lectureService;
        this.roomService = roomService;
        this.courseService = courseService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("lectures", lectureService.findAll());
        return "lecture";
    }

    @PostMapping("/searchLecturesById")
    public String searchUsersById(@RequestParam Long id, Model model) {
        model.addAttribute("lectures", Collections.singletonList(lectureService.findById(id)));
        return "lecture";
    }

    @GetMapping("/searchAll")
    public String searchAll(Model model) {
        model.addAttribute("lectures", lectureService.findAll());
        return "lecture";
    }

    @PostMapping("/addNew")
    public String addNew(@RequestParam String dateTime, @RequestParam Long roomId, @RequestParam Long courseId, Model model) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime);
        Long lectureId = lectureService.create(new Lecture(localDateTime, roomService.findById(roomId)));
        courseService.scheduleLecture(courseId, lectureId);
        return "redirect:/lecture/searchAll";
    }

    @GetMapping("deleteLecture/{id}")
    public String removeLectureFromCourse(@PathVariable Long id) throws DaoException {
        lectureService.deleteById(id);
        return "redirect:/lecture/searchAll";
    }

    @ModelAttribute("courseService")
    public CourseService courseService() {
        return courseService;
    }

    @ModelAttribute("rooms")
    public List<Room> rooms() { return roomService.findAll(); }

    @ModelAttribute("courses")
    public List<Course> courses() {
        return courseService.findAll();
    }

}
