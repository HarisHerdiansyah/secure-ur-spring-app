package io.github.harisherdiansyah.secureurspringapp.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Builder
public record BookResponseDTO(
        UUID id,
        String title,
        String author,
        Date publishedDate,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
