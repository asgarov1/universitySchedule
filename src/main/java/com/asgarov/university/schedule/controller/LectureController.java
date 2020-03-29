package com.asgarov.university.schedule.controller;

import com.asgarov.university.schedule.dao.exception.DaoException;
import com.asgarov.university.schedule.domain.Course;
import com.asgarov.university.schedule.domain.Lecture;
import com.asgarov.university.schedule.domain.Room;
import com.asgarov.university.schedule.service.CourseService;
import com.asgarov.university.schedule.service.LectureService;
import com.asgarov.university.schedule.service.RoomService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("lecture")
public class LectureController {

    private LectureService lectureService;
    private RoomService roomService;
    private CourseService courseService;
    private final static int INITIAL_AMOUNT_OF_RESULTS = 10;
    private final static String INITIAL_MESSAGE = "For performance reasons only the first " + INITIAL_AMOUNT_OF_RESULTS + " are displayed. Press showAll to see all results";
    private final static String CANT_FIND_BY_ID_MESSAGE = "Nothing found under this id";

    public LectureController(LectureService lectureService, RoomService roomService, CourseService courseService) {
        this.lectureService = lectureService;
        this.roomService = roomService;
        this.courseService = courseService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("lectures", lectureService.findAmount(INITIAL_AMOUNT_OF_RESULTS));
        model.addAttribute("message", INITIAL_MESSAGE);
        return "lecture";
    }

    @PostMapping("/searchLecturesById")
    public String searchLecturesById(@RequestParam Long id, HttpServletRequest request, Model model) {
        try {
            model.addAttribute("lectures", Collections.singletonList(lectureService.findById(id)));
        } catch (EmptyResultDataAccessException e) {
            model.addAttribute("message", CANT_FIND_BY_ID_MESSAGE);
            return "lecture";
        }

        return "lecture";
    }

    @GetMapping("/searchAll")
    public String searchAll(Model model) {
        model.addAttribute("lectures", lectureService.findAll());
        return "lecture";
    }

    @PostMapping("/addNew")
    public String addNew(@RequestParam String dateTime, @RequestParam Long roomId, @RequestParam Long courseId) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime);
        Long lectureId = lectureService.create(new Lecture(localDateTime, roomService.findById(roomId)));
        courseService.scheduleLecture(courseId, lectureId);
        return "redirect:/lecture/searchAll";
    }

    @GetMapping("deleteLecture/{id}")
    public String deleteLecture(@PathVariable Long id) throws DaoException {
        lectureService.deleteById(id);
        return "redirect:/lecture";
    }

    @PostMapping("/{id}/update")
    public String updateLecture(@PathVariable Long id, @RequestParam String dateTime, @RequestParam Long roomId) throws DaoException {
        Lecture lecture = lectureService.findById(id);
        lecture.setDateTime(LocalDateTime.parse(dateTime));
        lecture.setRoom(roomService.findById(roomId));
        lectureService.update(lecture);
        return "redirect:/lecture";
    }

    @ModelAttribute("courseService")
    public CourseService courseService() {
        return courseService;
    }

    @ModelAttribute("rooms")
    public List<Room> rooms() {
        return roomService.findAll();
    }

    @ModelAttribute("courses")
    public List<Course> courses() {
        return courseService.findAll();
    }
}
