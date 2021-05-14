package com.example.demo.services.rooms;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SaveRoomRequest {
    private int id;
    @NotEmpty
    private String name;
    private int roomTypeId;
}
