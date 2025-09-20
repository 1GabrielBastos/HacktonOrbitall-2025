package br.com.orbital.hackaton.ha.repositories;

import br.com.orbital.hackaton.ha.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    // Método para a funcionalidade "Listar todas as transações de um cliente"
    List<Transaction> findByCustomerId(UUID customerId);
}