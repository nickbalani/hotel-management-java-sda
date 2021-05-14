package com.example.demo.services.clients;

import com.example.demo.domain.Client;
import com.example.demo.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(final ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Optional<Client> findById(int id) {
        return clientRepository.findById(id);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public int save(SaveClientRequest request) {
        var dbItem = clientRepository.findById(request.getId());
        if (dbItem.isPresent()) {
            dbItem.get().setEmail(request.getName());
            dbItem.get().setPhoneNumber(request.getPhoneNumber());
            dbItem.get().setName(request.getEmail());
            clientRepository.save(dbItem.get());
            return dbItem.get().getId();
        }

        var newClient = Client.builder()
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .name(request.getName())
                .createdAt(LocalDateTime.now())
                .build();
        clientRepository.save(newClient);
        return newClient.getId();
    }

    @Override
    public void delete(int id) {
        var dbClient = clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id is invalid"));
        clientRepository.delete(dbClient);
    }
}
