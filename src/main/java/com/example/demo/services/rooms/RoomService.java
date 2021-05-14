package com.example.demo.services.rooms;

import com.example.demo.domain.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    Optional<Room> findById(int id);

    List<Room> findAll();

    int save(SaveRoomRequest request);

    void delete(int id);
}
