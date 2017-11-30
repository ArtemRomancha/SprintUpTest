package app.services.manufacturer;

import app.models.Manufacturer;
import app.models.Product;
import app.repositories.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * JPA implementation of userService
 */

@Service
@Primary
public class ManufacturerServiceJpaImpl implements ManufacturerService {
    @Autowired
    private ManufacturerRepository manufacturerRepo;

    @Override
    public List<Manufacturer> findAll() {
        return this.manufacturerRepo.findAll();
    }

    @Override
    public List<Product> findProducts(Long id) {
        return this.findById(id).getProducts();
    }

    @Override
    public Manufacturer findById(Long id) {
        return this.manufacturerRepo.findOne(id);
    }

    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        return this.manufacturerRepo.save(manufacturer);
    }

    @Override
    public Manufacturer edit(Manufacturer manufacturer) {
        return this.manufacturerRepo.save(manufacturer);
    }

    @Override
    public void deleteById(Long id) {
        this.manufacturerRepo.delete(id);
    }
}
