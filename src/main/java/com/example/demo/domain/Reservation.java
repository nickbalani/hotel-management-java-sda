package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    @ManyToOne
    @JoinColumn(name = "roomId", insertable = false, updatable = false)
    private Room room;
    private int roomId;
    @ManyToOne
    @JoinColumn(name = "clientId", insertable = false, updatable = false)
    private Client client;
    private int clientId;
    private LocalDateTime createdAt;
}
