package io.github.harisherdiansyah.secureurspringapp.dto;

import io.github.harisherdiansyah.secureurspringapp.model.Role;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponseDTO(UUID id, String email, String fullName, Role role, LocalDateTime createdAt,
                              LocalDateTime updatedAt) {
}
