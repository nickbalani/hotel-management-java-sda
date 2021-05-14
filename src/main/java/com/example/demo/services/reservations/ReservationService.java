package com.example.demo.services.reservations;

import com.example.demo.domain.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    Optional<Reservation> findById(int id);
    List<Reservation> findAll();
    int save(SaveReservationRequest request);
    void delete(int id);
}
