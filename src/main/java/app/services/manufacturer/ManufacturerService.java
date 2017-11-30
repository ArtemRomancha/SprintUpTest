package app.services.manufacturer;

import app.models.Manufacturer;
import app.models.Product;

import java.util.List;

/**
 * Interface for manufacturer data service
 */
public interface ManufacturerService {
        List<Manufacturer> findAll();
        List<Product> findProducts(Long id);
        Manufacturer findById(Long id);
        Manufacturer create(Manufacturer manufacturer);
        Manufacturer edit(Manufacturer manufacturer);
        void deleteById(Long id);
}
