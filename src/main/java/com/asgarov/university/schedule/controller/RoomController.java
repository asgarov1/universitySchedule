package com.asgarov.university.schedule.controller;

import com.asgarov.university.schedule.dao.exception.DaoException;
import com.asgarov.university.schedule.domain.Room;
import com.asgarov.university.schedule.service.RoomService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@RequestMapping("room")
public class RoomController {

    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("rooms", roomService.findAll());
        return "room";
    }

    @PostMapping("/searchRoomsById")
    public String searchRoomsById(@RequestParam Long id, Model model) {
        model.addAttribute("rooms", Collections.singletonList(roomService.findById(id)));
        return "room";
    }

    @GetMapping("/searchAll")
    public String searchAll(Model model) {
        model.addAttribute("rooms", roomService.findAll());
        return "room";
    }

    @PostMapping("/addNew")
    public String addNew(Room room) {
        roomService.create(room);
        return "redirect:/room/searchAll";
    }

    @GetMapping("deleteRoom/{id}")
    public String deleteRoom(@PathVariable Long id, Model model) {
        try {
            roomService.deleteById(id);
        } catch (DataIntegrityViolationException | DaoException e) {
            model.addAttribute("error", "Can't delete, as this room is being used for lecture(s)!");
            model.addAttribute("rooms", roomService.findAll());
            return "room";
        }
        return "redirect:/room/searchAll";
    }

    @PostMapping("/{id}/update")
    public String updateRoom(@PathVariable Long id, Room room) throws DaoException {
        room.setId(id);
        roomService.update(room);
        return "redirect:/room/searchAll";
    }

    @ModelAttribute("newRoom")
    public Room newRoom() {
        return new Room();
    }

}
