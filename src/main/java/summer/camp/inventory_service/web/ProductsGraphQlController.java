package summer.camp.inventory_service.web;

import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import summer.camp.inventory_service.dtos.ProductRequestDTO;
import summer.camp.inventory_service.entities.Category;
import summer.camp.inventory_service.entities.Product;
import summer.camp.inventory_service.repositories.CategoryRepository;
import summer.camp.inventory_service.repositories.ProductRepository;

import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class ProductsGraphQlController {
    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    @QueryMapping
    public List<Product>products()
    {
        return productRepository.findAll();
    }
    @QueryMapping
    public Product productById(@Argument String id)
    {
        return productRepository.findById(id).orElseThrow(()->
                    new RuntimeException(String.format("PRODUCT %s NOT FOUND",id))
                );
    }
    @QueryMapping
    public List<Category>categories()
    {
        return categoryRepository.findAll();
    }
    @QueryMapping
    public Category categoryById(@Argument Long id)
    {
        return categoryRepository.findById(id).orElseThrow(()->
                new RuntimeException(String.format("Category %s NOT FOUND",id))
        );
    }
    @MutationMapping
    public Product saveProduct(@Argument ProductRequestDTO product)
    {
        Category category=categoryRepository.findById(product.categoryId()).orElse(null);
        Product productToSave=new Product();
        productToSave.setId(UUID.randomUUID().toString());
        productToSave.setName(product.name());
        productToSave.setPrice(product.price());
        productToSave.setQuantity(product.quantity());
        productToSave.setCategory(category);
        return productRepository.save(productToSave);
    }
    @MutationMapping
    public Product updateProduct(@Argument ProductRequestDTO product,@Argument String id)
    {
        Category category=categoryRepository.findById(product.categoryId()).orElse(null);
        Product productToSave=productRepository.findById(id).orElseThrow(
                ()->new RuntimeException(String.format("Category %s NOT FOUND",id))
        );
        productToSave.setName(product.name());
        productToSave.setPrice(product.price());
        productToSave.setQuantity(product.quantity());
        productToSave.setCategory(category);
        return productRepository.save(productToSave);
    }
    @MutationMapping
    public void deleteProductById(@Argument String id)
    {
        productRepository.deleteById(id);
    }

}
