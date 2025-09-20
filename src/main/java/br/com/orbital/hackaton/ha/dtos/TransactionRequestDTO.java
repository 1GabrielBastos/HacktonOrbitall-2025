package br.com.orbital.hackaton.ha.dtos;

import br.com.orbital.hackaton.ha.entities.CardType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionRequestDTO(
        @NotNull(message = "O ID do cliente é obrigatório.")
        UUID customerId,

        @NotNull(message = "O valor da transação é obrigatório.")
        @Positive(message = "O valor da transação deve ser maior que zero.")
        BigDecimal amount,

        @NotNull(message = "O tipo de cartão é obrigatório.")
        CardType cardType // Usando o Enum para validação automática de DEBITO/CREDITO
) {}