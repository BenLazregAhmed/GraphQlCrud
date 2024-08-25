package summer.camp.inventory_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import summer.camp.inventory_service.entities.Category;
import summer.camp.inventory_service.entities.Product;
import summer.camp.inventory_service.repositories.CategoryRepository;
import summer.camp.inventory_service.repositories.ProductRepository;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(CategoryRepository categoryRepository,
										ProductRepository productRepository){
		return args -> {
			Random random=new Random();
			List.of("Computer","Printer","Smartphone").forEach(cat->{
				Category category=Category.builder().name(cat).build();
				categoryRepository.save(category);
			});
			categoryRepository.findAll().forEach(category->{
				for (int i = 0; i < 10; i++) {
					Product product=Product.builder()
							.id(UUID.randomUUID().toString())
							.name("Computer "+i)
							.price(100+Math.random()*50000)
							.quantity(random.nextInt(100))
							.category(category)
							.build();
					productRepository.save(product);
				}
			});
		};
	}

}
