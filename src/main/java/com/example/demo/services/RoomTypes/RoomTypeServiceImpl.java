package com.example.demo.services.RoomTypes;

import com.example.demo.domain.RoomType;
import com.example.demo.repositories.RoomTypeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {
    private final RoomTypeRepository roomTypeRepository;

    public RoomTypeServiceImpl(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    @Override
    public Optional<RoomType> findById(int id) {
        return this.roomTypeRepository.findById(id);
    }

    @Override
    public List<RoomType> findAll() {
        return this.roomTypeRepository.findAll();
    }

    @Override
    public int save(SaveRoomTypeRequest request) {
        var dbItem = roomTypeRepository.findById(request.getId());
        if (dbItem.isPresent()) {
            dbItem.get().setName(request.getName());
            dbItem.get().setPhoto(request.getPhoto());
            roomTypeRepository.save(dbItem.get());
            return dbItem.get().getId();
        }

        var newItem = RoomType.builder()
                .name(request.getName())
                .photo(request.getPhoto())
                .createdAt(LocalDateTime.now())
                .build();
        roomTypeRepository.save(newItem);
        return newItem.getId();
    }

    @Override
    public void delete(int id) {
        var item = roomTypeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id is invalid"));
        roomTypeRepository.delete(item);
    }
}
