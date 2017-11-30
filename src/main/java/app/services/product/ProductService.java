package app.services.product;

import app.models.Product;
import java.util.List;

/**
 * Interface for manufacturer data service
 */
public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    Product create(Product product);
    Product edit(Product product);
    void deleteById(Long id);
}
