package com.asgarov.university.schedule.controller;

import com.asgarov.university.schedule.Runner;
import com.asgarov.university.schedule.domain.Room;
import com.asgarov.university.schedule.service.RoomService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ContextConfiguration(classes = {Runner.class})
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RoomControllerTest {

    private MockMvc mockMvc;

    private static final String ROOM_PATH = "/room";

    @Autowired
    private RoomController roomController;

    @Autowired
    private RoomService roomService;

    @BeforeAll
    void setup() {
        FormattingConversionService conversionService = new FormattingConversionService();
        conversionService.addConverter(new StringToRoomConverter());

        mockMvc = MockMvcBuilders
                .standaloneSetup(roomController)
                .setConversionService(conversionService)
                .setViewResolvers(viewResolver())
                .build();
    }

    @Test
    public void indexShouldReturnRoomViewWithStatusOk() throws Exception {
        this.mockMvc.perform(get(ROOM_PATH))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("room"));
    }

    @Test
    public void searchRoomsByIdShouldWork() throws Exception {
        this.mockMvc.perform(get(ROOM_PATH + "/searchRoomsById")
                .param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("room"));
    }

    @Test
    public void addNewRoomShouldWork() throws Throwable {
        mockMvc.perform(post(ROOM_PATH)
                .param("room", "TestRoom"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:" + ROOM_PATH));
    }

    @Test
    public void deleteRoomShouldWork() throws Exception {
        Room room = new Room("TestRoom");
        roomService.create(room);

        this.mockMvc.perform(delete(ROOM_PATH + "/" + room.getId()))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/room"));
    }

    @Test
    public void updateRoomShouldWork() throws Exception {
        String roomId = "1";
        this.mockMvc.perform(put(ROOM_PATH + "/" + roomId)
                .param("roomName", "TestRoom"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:" + ROOM_PATH));
    }

    private static class StringToRoomConverter implements Converter<String, Room> {
        @Override
        public Room convert(String from) {
            return new Room(from);
        }
    }

}
