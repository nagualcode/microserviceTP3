package br.nagualcode.kitchenorders.repository;

import br.nagualcode.kitchenorders.domain.KitchenOrder;
import org.springframework.data.repository.CrudRepository;

public interface KitchenOrderRepository extends CrudRepository<KitchenOrder, Long> {

}
