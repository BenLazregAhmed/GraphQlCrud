package summer.camp.inventory_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import summer.camp.inventory_service.entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}