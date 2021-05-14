package com.example.demo.services.reservations;

import com.example.demo.domain.Client;
import com.example.demo.domain.Reservation;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, ClientRepository clientRepository) {
        this.reservationRepository = reservationRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public Optional<Reservation> findById(int id) {
        return reservationRepository.findById(id);
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Transactional
    @Override
    public int save(SaveReservationRequest request) {
        var dbItem = reservationRepository.findById(request.getId());
        if (dbItem.isPresent()) {

            var client = clientRepository
                    .findById(dbItem.get().getClientId()).orElseThrow(() -> new IllegalArgumentException("Invalid client id"));
            client.setPhoneNumber(request.getClientPhone());
            client.setEmail(request.getClientEmail());
            client.setName(request.getClientName());
            clientRepository.save(client);

            dbItem.get().setCheckIn(request.getCheckIn());
            dbItem.get().setCheckOut(request.getCheckOut());
            dbItem.get().setRoomId(request.getRoomId());
            reservationRepository.save(dbItem.get());

            return dbItem.get().getId();
        }

        var newClient = Client.builder()
                .email(request.getClientEmail())
                .phoneNumber(request.getClientPhone())
                .name(request.getClientName())
                .createdAt(LocalDateTime.now())
                .build();
        clientRepository.save(newClient);

        var newReservation = Reservation.builder()
                .checkIn(request.getCheckIn())
                .checkOut(request.getCheckOut())
                .clientId(newClient.getId())
                .roomId(request.getRoomId())
                .createdAt(LocalDateTime.now())
                .build();
        reservationRepository.save(newReservation);

        return newReservation.getId();
    }

    @Override
    public void delete(int id) {
        var dbItem = reservationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id is invalid"));
        reservationRepository.delete(dbItem);
    }
}
