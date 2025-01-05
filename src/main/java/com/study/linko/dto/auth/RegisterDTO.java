package com.study.linko.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(
    @NotNull
    @NotBlank
    String username,
    @NotNull
    @NotBlank
    String email,
    @NotNull
    @NotBlank
    String password
) {
    
}
