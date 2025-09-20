package br.com.orbital.hackaton.ha.services;

import br.com.orbital.hackaton.ha.dtos.CustomerRequestDTO;
import br.com.orbital.hackaton.ha.dtos.CustomerResponseDTO;
import br.com.orbital.hackaton.ha.entities.Customer;
import br.com.orbital.hackaton.ha.repositories.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // Lombok: Injeta as dependências via construtor (final)
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<CustomerResponseDTO> listAllActiveCustomers() {
        return customerRepository.findAllByActiveTrue().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public CustomerResponseDTO findCustomerById(UUID id) {
        Customer customer = findCustomerEntityById(id);
        return toResponseDTO(customer);
    }

    public Customer findCustomerEntityById(UUID id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com o ID: " + id));
    }

    public CustomerResponseDTO createCustomer(CustomerRequestDTO requestDTO) {
        Customer newCustomer = new Customer();
        newCustomer.setFullName(requestDTO.fullName());
        newCustomer.setEmail(requestDTO.email());
        newCustomer.setPhone(requestDTO.phone());
        newCustomer.setCreatedAt(LocalDateTime.now());
        newCustomer.setUpdatedAt(LocalDateTime.now());
        newCustomer.setActive(true); // Regra: novo cliente sempre ativo

        Customer savedCustomer = customerRepository.save(newCustomer);
        return toResponseDTO(savedCustomer);
    }

    public CustomerResponseDTO updateCustomer(UUID id, CustomerRequestDTO requestDTO) {
        Customer customer = findCustomerEntityById(id);

        customer.setFullName(requestDTO.fullName());
        customer.setEmail(requestDTO.email());
        customer.setPhone(requestDTO.phone());
        customer.setUpdatedAt(LocalDateTime.now()); // Atualiza a data de modificação

        Customer updatedCustomer = customerRepository.save(customer);
        return toResponseDTO(updatedCustomer);
    }

    public void deleteCustomer(UUID id) {
        Customer customer = findCustomerEntityById(id);

        // Soft delete: Apenas inativamos o cliente
        customer.setActive(false);
        customer.setUpdatedAt(LocalDateTime.now());
        customerRepository.save(customer);
    }

    // Método utilitário para converter Entidade em DTO de resposta
    private CustomerResponseDTO toResponseDTO(Customer customer) {
        return new CustomerResponseDTO(
                customer.getId(),
                customer.getFullName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getCreatedAt(),
                customer.getUpdatedAt(),
                customer.isActive()
        );
    }
}