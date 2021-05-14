package com.example.demo.services.RoomTypes;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SaveRoomTypeRequest {
    private int id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String photo;
}
