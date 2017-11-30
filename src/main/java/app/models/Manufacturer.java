package app.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Manufacturer entity
 */

@Entity
@Table(name = "manufacturer")
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manufacturer_id")
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Введите название")
    private String name;

    @Column(name = "description")
    @NotEmpty(message = "Введите название")
    private String description;

    @Column(name = "address")
    @NotEmpty(message = "Введите название")
    private String address;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "manufacturer")
    private List<Product> products = new LinkedList<>();

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Manufacturer() {
    }

    public Manufacturer(String name, String description, String address) {
        this.name = name;
        this.description = description;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Manufacturer{" + "id=" + id +
                ", name=" + name +
                ", description=" + description +
                ", address=" + address + "}";
    }
}