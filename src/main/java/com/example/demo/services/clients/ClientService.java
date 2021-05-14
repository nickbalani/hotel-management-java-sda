package com.example.demo.services.clients;

import com.example.demo.domain.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Optional<Client> findById(int id);

    List<Client> findAll();

    int save(SaveClientRequest request);

    void delete(int id);
}
