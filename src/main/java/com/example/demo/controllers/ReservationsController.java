package com.example.demo.controllers;

import com.example.demo.domain.Reservation;
import com.example.demo.services.reservations.ReservationService;
import com.example.demo.services.reservations.SaveReservationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationsController {
    private final ReservationService reservationService;

    public ReservationsController(final ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> get(@PathVariable int id) {
        var item = this.reservationService.findById(id);

        return item.isPresent()
                ? ResponseEntity.ok(item.get())
                : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public List<Reservation> getAll() {
        return reservationService.findAll();
    }

    @PostMapping
    public int save(@RequestBody @Valid SaveReservationRequest request) {
        return reservationService.save(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        reservationService.delete(id);
    }
}
