package com.example.demo.services.rooms;

import com.example.demo.domain.Room;
import com.example.demo.repositories.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Optional<Room> findById(int id) {
        return roomRepository.findById(id);
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public int save(SaveRoomRequest request) {
        var dbItem = roomRepository.findById(request.getId());
        if (dbItem.isPresent()) {
            dbItem.get().setName(request.getName());
            dbItem.get().setRoomTypeId(request.getRoomTypeId());
            roomRepository.save(dbItem.get());
            return dbItem.get().getId();
        }

        var newItem = Room.builder()
                .name(request.getName())
                .roomTypeId(request.getRoomTypeId())
                .createdAt(LocalDateTime.now())
                .build();
        roomRepository.save(newItem);
        return newItem.getId();
    }

    @Override
    public void delete(int id) {
        var item = roomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id is invalid"));
        roomRepository.delete(item);
    }
}
