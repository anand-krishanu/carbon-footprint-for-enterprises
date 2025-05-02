package com.ecotrack.carbon_tracker.dto;

import com.ecotrack.carbon_tracker.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDTO {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private Role role;
}

