package app.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;

/**
 * Product entity
 */

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Введите название")
    private String name;

    @Column(name = "description")
    @NotEmpty(message = "Введите название")
    private String description;

    @Column(name = "price")
    @Min(
            value = 1,
            message = "There must be at least {value} seat${value > 1 ? 's' : ''}"
    )
    private int price;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Product() {
    }

    public Product(String name, String description, int price, Manufacturer manufacturer) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id +
                ", name=" + name +
                ", description=" + description +
                ", price=" + price +
                ", manufacturer" + manufacturer + "}";
    }
}
