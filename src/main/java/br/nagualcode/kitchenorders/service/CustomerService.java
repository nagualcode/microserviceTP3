package br.nagualcode.kitchenorders.service;

import br.nagualcode.kitchenorders.domain.Customer;
import br.nagualcode.kitchenorders.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Mono<Customer> createCustomer(Customer customer) {
        return Mono.fromSupplier(() -> customerRepository.save(customer));
    }

    public Mono<Customer> getCustomerById(Long id) {
        return Mono.fromSupplier(() -> customerRepository.findById(id).orElse(null));
    }

    public Mono<Void> deleteCustomer(Long id) {
        return Mono.fromRunnable(() -> customerRepository.deleteById(id));
    }
}
