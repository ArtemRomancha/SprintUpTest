package app.repositories;

import app.models.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Manufacturer data repository
 */
@Repository("manufacturerRepository")
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
}






