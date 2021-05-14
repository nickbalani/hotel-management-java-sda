package com.example.demo.services.RoomTypes;

import com.example.demo.domain.RoomType;

import java.util.List;
import java.util.Optional;

public interface RoomTypeService {
    Optional<RoomType> findById(int id);
    List<RoomType> findAll();
    int save(SaveRoomTypeRequest request);
    void delete(int id);
}
