package br.com.orbital.hackaton.ha.services;

import br.com.orbital.hackaton.ha.dtos.TransactionRequestDTO;
import br.com.orbital.hackaton.ha.dtos.TransactionResponseDTO;
import br.com.orbital.hackaton.ha.entities.Customer;
import br.com.orbital.hackaton.ha.entities.Transaction;
import br.com.orbital.hackaton.ha.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CustomerService customerService; // Reutiliza o serviço de cliente

    public TransactionResponseDTO createTransaction(TransactionRequestDTO requestDTO) {
        // Regra: Não permitir transações para clientes inexistentes
        Customer customer = customerService.findCustomerEntityById(requestDTO.customerId());

        Transaction newTransaction = new Transaction();
        newTransaction.setCustomerId(customer.getId());
        newTransaction.setAmount(requestDTO.amount());
        newTransaction.setCardType(requestDTO.cardType());
        newTransaction.setCreatedAt(LocalDateTime.now());

        Transaction savedTransaction = transactionRepository.save(newTransaction);
        return toResponseDTO(savedTransaction);
    }

    public List<TransactionResponseDTO> listTransactionsByCustomerId(UUID customerId) {
        // Valida se o cliente existe antes de buscar as transações
        customerService.findCustomerEntityById(customerId);

        return transactionRepository.findByCustomerId(customerId).stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    private TransactionResponseDTO toResponseDTO(Transaction transaction) {
        return new TransactionResponseDTO(
                transaction.getId(),
                transaction.getCustomerId(),
                transaction.getAmount(),
                transaction.getCardType(),
                transaction.getCreatedAt()
        );
    }
}