package app.services.manufacturer;

import app.models.Product;
import app.models.Manufacturer;
import app.services.manufacturer.ManufacturerService;
import app.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * STUB implementation userService
 */
@Service
public class ManufacturerServiceStubImpl implements ManufacturerService {
    @Autowired
    ProductService productService;

    private List<Manufacturer> manufacturers = new ArrayList<>() {{
        add(new Manufacturer("Samsung", "", "Kyiv"));
        add(new Manufacturer("Apple", "", "Kyiv"));
        add(new Manufacturer("Nokia", "", "Kyiv"));
        add(new Manufacturer("Xiaomi", "", "Kyiv"));
    }};

    @Override
    public List<Manufacturer> findAll() {
        return this.manufacturers;
    }

    @Override
    public List<Product> findProducts(Long id) {
        return productService.findAll().stream()
                .filter(p -> Objects.equals(p.getManufacturer().getId(), id))
                .collect(Collectors.toList());
    }

    @Override
    public Manufacturer findById(Long id) {
        return this.manufacturers.stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        manufacturer.setId(this.manufacturers.stream().mapToLong(
                p -> p.getId()).max().getAsLong() + 1);
        this.manufacturers.add(manufacturer);
        return manufacturer;
    }

    @Override
    public Manufacturer edit(Manufacturer manufacturer) throws RuntimeException {
        for(int i = 0; i < this.manufacturers.size(); i++)  {
            if(Objects.equals(this.manufacturers.get(i).getId(), manufacturer.getId())) {
                this.manufacturers.set(i, manufacturer);
                return manufacturer;
            }
        }
        throw new RuntimeException("Product not found:" + manufacturer.getId());
    }

    @Override
    public void deleteById(Long id) throws RuntimeException {
        for(int i = 0; i < this.manufacturers.size(); i++)  {
            if(Objects.equals(this.manufacturers.get(i).getId(), id)) {
                this.manufacturers.remove(i);
                return;
            }
        }
        throw new RuntimeException("Product not found:" + id);
    }
}