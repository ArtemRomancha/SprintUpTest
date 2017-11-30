package app.services.product;

import app.models.Manufacturer;
import app.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * STUB implementation of userService
 */
@Service
public class ProductServiceStubImpl implements ProductService {
    private List<Product> products = new ArrayList<>() {{
        add(new Product("IPhone 6", "", 24000, new Manufacturer("Apple", "", "")));
        add(new Product("Samsung Wave 2", "", 24000, new Manufacturer("Samsung", "", "")));
        add(new Product("IPhone 8", "", 24000, new Manufacturer("Apple", "", "")));
        add(new Product("IPhone *", "", 24000, new Manufacturer("Apple", "", "")));
    }};

    @Override
    public List<Product> findAll() {
        return this.products;
    }

    @Override
    public Product findById(Long id) {
        return this.products.stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Product create(Product product) {
        product.setId(this.products.stream().mapToLong(
                p -> p.getId()).max().getAsLong() + 1);
        this.products.add(product);
        return product;
    }

    @Override
    public Product edit(Product product) throws RuntimeException {
        for (int i = 0; i < this.products.size(); i++) {
            if (Objects.equals(this.products.get(i).getId(), product.getId())) {
                this.products.set(i, product);
                return product;
            }
        }
        throw new RuntimeException("Product not found:" + product.getId());
    }

    @Override
    public void deleteById(Long id) throws RuntimeException {
        for (int i = 0; i < this.products.size(); i++) {
            if (Objects.equals(this.products.get(i).getId(), id)) {
                this.products.remove(i);
                return;
            }
        }
        throw new RuntimeException("Product not found:" + id);
    }
}
