package br.com.orbital.hackaton.ha.dtos;

import br.com.orbital.hackaton.ha.entities.CardType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionResponseDTO(
        UUID id,
        UUID customerId,
        BigDecimal amount,
        CardType cardType,
        LocalDateTime createdAt
) {}