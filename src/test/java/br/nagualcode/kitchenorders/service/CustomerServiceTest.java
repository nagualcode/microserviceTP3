package br.nagualcode.kitchenorders.service;

import br.nagualcode.kitchenorders.domain.Customer;
import br.nagualcode.kitchenorders.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@Testcontainers
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Container
    public static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("test")
            .withUsername("postgres")
            .withPassword("password");

    @DynamicPropertySource
    static void configureTestDatabase(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
    }

    @BeforeEach
    void setUp() {
        customerRepository.deleteAll();
    }

    @Test
    void testCreateCustomer() {
        Customer customer = new Customer();
        customer.setEmail("customer@test.com");
        customer.setName("Test Customer");

        Mono<Customer> createdCustomer = customerService.createCustomer(customer);

        StepVerifier.create(createdCustomer)
                .expectNextMatches(savedCustomer -> savedCustomer.getId() != null && savedCustomer.getEmail().equals("customer@test.com"))
                .verifyComplete();
    }

    @Test
    void testGetCustomerById() {
        Customer customer = new Customer();
        customer.setEmail("customer2@test.com");
        customer.setName("Another Customer");
        Customer savedCustomer = customerRepository.save(customer);

        Mono<Customer> retrievedCustomer = customerService.getCustomerById(savedCustomer.getId());

        StepVerifier.create(retrievedCustomer)
                .expectNextMatches(c -> c.getId().equals(savedCustomer.getId()) && c.getEmail().equals("customer2@test.com"))
                .verifyComplete();
    }
}
