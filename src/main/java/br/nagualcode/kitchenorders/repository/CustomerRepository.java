package br.nagualcode.kitchenorders.repository;

import br.nagualcode.kitchenorders.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
  
}
