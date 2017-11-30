package app.services.product;

import app.models.Product;
import app.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * JPA implementation of userService
 */
@Service
@Primary
public class ProductServiceJpaImpl implements ProductService {
    @Autowired
    private ProductRepository productRepo;

    @Override
    public List<Product> findAll() {
        return this.productRepo.findAll();
    }

    @Override
    public Product findById(Long id) {
        return this.productRepo.findOne(id);
    }

    @Override
    public Product create(Product product) {
        return this.productRepo.save(product);
    }

    @Override
    public Product edit(Product product) {
        return this.productRepo.save(product);
    }

    @Override
    public void deleteById(Long id) {
        this.productRepo.delete(id);
    }
}
