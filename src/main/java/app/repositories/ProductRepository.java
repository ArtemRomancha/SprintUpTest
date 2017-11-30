package app.repositories;

import app.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Product data repository
 */
@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, Long> {
}


