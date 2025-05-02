package com.ecotrack.carbon_tracker.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponseDTO {
    private String token;

    public AuthResponseDTO(String token) {
        this.token = token;
    }
}

