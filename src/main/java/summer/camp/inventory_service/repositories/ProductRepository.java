package summer.camp.inventory_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import summer.camp.inventory_service.entities.Product;

public interface ProductRepository extends JpaRepository<Product,String> {
}
