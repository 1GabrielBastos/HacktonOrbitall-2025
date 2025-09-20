package br.com.orbital.hackaton.ha.controllers;

import br.com.orbital.hackaton.ha.dtos.TransactionRequestDTO;
import br.com.orbital.hackaton.ha.dtos.TransactionResponseDTO;
import br.com.orbital.hackaton.ha.services.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> create(@RequestBody @Valid TransactionRequestDTO request) {
        TransactionResponseDTO response = transactionService.createTransaction(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponseDTO>> getByCustomerId(@RequestParam UUID customerId) {
        List<TransactionResponseDTO> response = transactionService.listTransactionsByCustomerId(customerId);
        return ResponseEntity.ok(response);
    }
}