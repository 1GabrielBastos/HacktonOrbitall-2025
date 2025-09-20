package br.com.orbital.hackaton.ha.repositories;

import br.com.orbital.hackaton.ha.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    // Método para a funcionalidade "Listar todos os clientes ativos"
    List<Customer> findAllByActiveTrue();
}