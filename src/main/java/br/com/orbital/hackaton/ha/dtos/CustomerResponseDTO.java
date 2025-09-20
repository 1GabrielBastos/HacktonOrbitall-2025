package br.com.orbital.hackaton.ha.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record CustomerResponseDTO(
        UUID id,
        String fullName,
        String email,
        String phone,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        boolean active
) {}