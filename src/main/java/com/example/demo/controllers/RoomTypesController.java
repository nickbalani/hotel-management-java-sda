package com.example.demo.controllers;

import com.example.demo.domain.RoomType;
import com.example.demo.services.RoomTypes.RoomTypeService;
import com.example.demo.services.RoomTypes.SaveRoomTypeRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/roomTypes")
public class RoomTypesController {
    private final RoomTypeService roomTypeService;

    public RoomTypesController(final RoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomType> get(@PathVariable int id) {
        var item = this.roomTypeService.findById(id);

        return item.isPresent()
                ? ResponseEntity.ok(item.get())
                : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public List<RoomType> getAll() {
        return roomTypeService.findAll();
    }

    @PostMapping
    public int save(@RequestBody @Valid SaveRoomTypeRequest request) {
        return roomTypeService.save(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        roomTypeService.delete(id);
    }
}
