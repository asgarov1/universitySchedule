package com.asgarov.university.schedule.controller;

import com.asgarov.university.schedule.Runner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.asgarov.university.schedule.controller.LectureControllerTest.StringToLectureDTOConverter;
import static com.asgarov.university.schedule.util.Resolver.viewResolver;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ContextConfiguration(classes = {Runner.class})
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CourseControllerTest {

    private static final String COURSE_PATH = "/course";

    MockMvc mockMvc;

    @Autowired
    CourseController courseController;

    @BeforeAll
    void setup() {
        FormattingConversionService conversionService = new FormattingConversionService();
        conversionService.addConverter(new StringToLectureDTOConverter());

        mockMvc = MockMvcBuilders
                .standaloneSetup(courseController)
                .setConversionService(conversionService)
                .setViewResolvers(viewResolver())
                .build();
    }

    @Test
    public void indexShouldReturnCourseViewWithStatusOk() throws Exception {
        this.mockMvc.perform(get(COURSE_PATH)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("course"));
    }

    @Test
    public void searchCoursesByIdShouldWork() throws Exception {
        this.mockMvc.perform(get(COURSE_PATH + "/searchCoursesById").param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("course"));
    }

    @Test
    public void registerStudentShouldWork() throws Exception {
        String courseId = "1";
        String studentId = "4";

        this.mockMvc.perform(post(COURSE_PATH + "/" + courseId + "/registerStudent")
                .param("studentId", studentId))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/course/" + courseId + "/students"));
    }

    @Test
    public void addLectureShouldWork() throws Exception {
        String courseId = "1";
        mockMvc.perform(post(COURSE_PATH + "/" + courseId + "/addLecture")
                .param("lectureDTO", "2011-11-11,01:01,1,1"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/course/" + courseId + "/lectures"));
    }

    @Test
    public void addNewCourseShouldWork() throws Exception {
        String name = "New Course";
        String professorId = "1";

        this.mockMvc.perform(post(COURSE_PATH)
                .param("name", name)
                .param("professorId", professorId))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:" + COURSE_PATH));
    }

    @DirtiesContext
    @Test
    public void updateCourseShouldWork() throws Exception {
        String courseId = "2";
        String courseName = "Test Course";
        String professorId = "2";

        this.mockMvc.perform(put(COURSE_PATH + "/" + courseId)
                .param("courseName", courseName)
                .param("professorId", professorId))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:" + COURSE_PATH));
    }

    @Test
    public void showCourseLecturesShouldWork() throws Exception {
        String courseId = "1";

        this.mockMvc.perform(get(COURSE_PATH + "/" + courseId + "/lectures"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("courseLectures"));
    }

    @Test
    public void showCourseStudentsShouldWork() throws Exception {
        String courseId = "2";

        this.mockMvc.perform(get(COURSE_PATH + "/" + courseId + "/students"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("courseStudents"));
    }

    @Test
    public void deleteCourseShouldWork() throws Exception {
        String courseId = "1";

        this.mockMvc.perform(delete(COURSE_PATH + "/" + courseId))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:" + COURSE_PATH));
    }

    @Test
    public void removeStudentFromCourseShouldWork() throws Exception {
        String courseId = "1";
        String studentId = "1";

        this.mockMvc.perform(delete(COURSE_PATH + "/" + courseId + "/students/" + studentId))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:" + COURSE_PATH + "/" + courseId + "/students"));
    }

    @Test
    public void removeLectureFromCourseShouldWork() throws Exception {
        String courseId = "1";
        String lectureId = "1";

        this.mockMvc.perform(delete(COURSE_PATH + "/" + courseId + "/lectures/" + lectureId))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:" + COURSE_PATH + "/" + courseId + "/lectures"));
    }

}
