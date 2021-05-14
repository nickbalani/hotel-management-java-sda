package com.example.demo.controllers;

import com.example.demo.domain.Room;
import com.example.demo.services.rooms.RoomService;
import com.example.demo.services.rooms.SaveRoomRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomsController {
    private final RoomService roomService;

    public RoomsController(final RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> get(@PathVariable int id) {
        var item = this.roomService.findById(id);

        return item.isPresent()
                ? ResponseEntity.ok(item.get())
                : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public List<Room> getAll() {
        return roomService.findAll();
    }

    @PostMapping
    public int save(@RequestBody @Valid SaveRoomRequest request) {
        return roomService.save(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        roomService.delete(id);
    }
}
