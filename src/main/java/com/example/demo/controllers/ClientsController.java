package com.example.demo.controllers;

import com.example.demo.domain.Client;
import com.example.demo.services.clients.ClientService;
import com.example.demo.services.clients.SaveClientRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientsController {

    private final ClientService clientService;

    public ClientsController(final ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> get(@PathVariable int id) {
        var item = this.clientService.findById(id);

        return item.isPresent()
                ? ResponseEntity.ok(item.get())
                : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public List<Client> getAll() {
        return clientService.findAll();
    }

    @PostMapping
    public int save(@RequestBody @Valid SaveClientRequest request) {
        return clientService.save(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        clientService.delete(id);
    }
}
