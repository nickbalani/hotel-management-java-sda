package com.example.demo.services.clients;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveClientRequest {
    private int id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String email;
    private String phoneNumber;
}
