package br.com.orbital.hackaton.ha.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Armazenamos apenas o ID do cliente, conforme solicitado.
    @Column(nullable = false)
    private UUID customerId;

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING) // Informa ao JPA para salvar o nome do Enum (DEBITO/CREDITO) como String
    @Column(nullable = false)
    private CardType cardType;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // O enunciado não pede 'active' para Transaction, então não incluiremos. Se necessário, adicione aqui.
}