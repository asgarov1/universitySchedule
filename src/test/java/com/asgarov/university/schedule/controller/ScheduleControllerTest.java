package com.asgarov.university.schedule.controller;

import com.asgarov.university.schedule.Runner;
import com.asgarov.university.schedule.domain.dto.ScheduleRequestDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.asgarov.university.schedule.util.Resolver.viewResolver;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ContextConfiguration(classes = {Runner.class})
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ScheduleControllerTest {

    private MockMvc mockMvc;

    private static final String SCHEDULE_PATH = "/schedule";

    @Autowired
    private ScheduleController scheduleController;

    @BeforeAll
    void setup() {
        FormattingConversionService conversionService = new FormattingConversionService();
        conversionService.addConverter(new StringToScheduleRequestDTOConverter());

        mockMvc = MockMvcBuilders
                .standaloneSetup(scheduleController)
                .setConversionService(conversionService)
                .setViewResolvers(viewResolver())
                .build();
    }

    @Test
    public void indexShouldReturnScheduleViewWithStatusOk() throws Exception {
        this.mockMvc.perform(get(SCHEDULE_PATH))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("schedule"));
    }

    @Test
    public void showScheduleShouldWork() throws Exception {
        this.mockMvc.perform(get(SCHEDULE_PATH + "/showSchedule")
                .param("scheduleRequestDTO", "1,STUDENT,2020-04-01,2020-04-10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("schedule"));
    }

    private static class StringToScheduleRequestDTOConverter implements Converter<String, ScheduleRequestDTO> {
        @Override
        public ScheduleRequestDTO convert(String from) {
            String[] data = from.split(",");
            return new ScheduleRequestDTO(Long.valueOf(data[0]), data[1], data[2], data[3]);
        }
    }

}
