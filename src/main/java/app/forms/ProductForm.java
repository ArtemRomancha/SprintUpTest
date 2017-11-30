package app.forms;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Validation form class for create and update product
 */
public class ProductForm {
    private long id;

    @NotNull
    @Size(min = 1, max = 63, message = "Название должно быть больше 1 и меньше 63 символов")
    private String name;

    @Size(min = 1, max = 255, message = "Описание должно быть больше 1 и меньше 255 символов")
    private String description;

    @Min(1)
    private int price = 1;

    @NotNull
    private Long manufacturer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Long getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Long manufacturer) {
        this.manufacturer = manufacturer;
    }
}
