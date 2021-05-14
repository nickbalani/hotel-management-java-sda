package com.example.demo.services.reservations;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SaveReservationRequest {
    private int id;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private int roomId;
    private String clientName;
    private String clientEmail;
    private String clientPhone;
    private LocalDateTime createdAt;
}
