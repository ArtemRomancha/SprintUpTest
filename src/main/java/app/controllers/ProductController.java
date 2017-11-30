package app.controllers;

import app.forms.ProductForm;
import app.models.Manufacturer;
import app.models.Product;
import app.services.manufacturer.ManufacturerService;
import app.services.notification.NotificationService;
import app.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller for mapping product's pages
 */
@Controller
public class ProductController {
    /**
     * Service to access product data
     */
    @Autowired
    private ProductService productService;

    /**
     * Service to access manufacturer data
     */
    @Autowired
    private ManufacturerService manufacturerService;

    /**
     * Service to show notifications
     */
    @Autowired
    private NotificationService notificationService;

    /**
     * Mapping to show all products
     *
     * @param model
     * @return view
     */
    @RequestMapping("/products")
    public String index(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "product/index";
    }

    /**
     * Mapping to show {id}-th product information
     *
     * @param id
     * @param model
     * @return view or redirect
     */
    @RequestMapping("/product/{id}")
    public String view(@PathVariable("id") Long id, Model model) {
        Product product = productService.findById(id);
        if (product == null) {
            notificationService.addErrorMessage("Cannot find product #" + id);
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        return "product/view";
    }

    /**
     * Mapping to show product create form
     *
     * @param model
     * @param productForm
     * @return view
     */
    @RequestMapping("/product/create")
    public String create(Model model, ProductForm productForm) {
        List<Manufacturer> manufacturers = manufacturerService.findAll();

        model.addAttribute("manufacturers", manufacturers);
        return "product/create";
    }

    /**
     * Mapping to validate and create new product
     *
     * @param model
     * @param productForm
     * @param bindingResult
     * @return view or redirect
     */
    @RequestMapping(value = "/product/create", method = RequestMethod.POST)
    public String create(Model model, @Valid ProductForm productForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("manufacturers", manufacturerService.findAll());
            return "/product/create";
        }

        Manufacturer manufacturer = manufacturerService.findById(productForm.getManufacturer());
        Product newProduct = new Product(productForm.getName(), productForm.getDescription(), productForm.getPrice(), manufacturer);
        newProduct = productService.create(newProduct);

        notificationService.addInfoMessage("Продукт " + newProduct.getName() + " успешно добавлен");
        return "redirect:/products";
    }

    /**
     * Mapping to show {id}-th product edit form
     *
     * @param id
     * @param model
     * @param productForm
     * @return view or redirect
     */
    @RequestMapping("/product/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model, ProductForm productForm) {
        List<Manufacturer> manufacturers = manufacturerService.findAll();
        Product product = productService.findById(id);
        if (product == null) {
            notificationService.addErrorMessage("Cannot find product #" + id);
            return "redirect:/products";
        }

        productForm.setId(product.getId());
        productForm.setName(product.getName());
        productForm.setDescription(product.getDescription());
        productForm.setPrice(product.getPrice());
        productForm.setManufacturer(product.getManufacturer().getId());

        model.addAttribute("manufacturers", manufacturers);
        return "/product/edit";
    }

    /**
     * Mapping to validate and update {id}-th product
     *
     * @param id
     * @param model
     * @param productForm
     * @param bindingResult
     * @return view or redirect
     */
    @RequestMapping(value = "/product/{id}/edit", method = RequestMethod.POST)
    public String edit(@PathVariable("id") Long id, Model model, @Valid ProductForm productForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<Manufacturer> manufacturers = manufacturerService.findAll();
            model.addAttribute("manufacturers", manufacturers);
            return "/product/edit";
        }

        Product product = productService.findById(id);
        product.setName(productForm.getName());
        product.setDescription(productForm.getDescription());
        product.setPrice(productForm.getPrice());
        product.setManufacturer(manufacturerService.findById(productForm.getManufacturer()));

        product = productService.edit(product);

        notificationService.addInfoMessage("Продукт " + product.getName() + " успешно обновлен");
        return "redirect:/products";
    }

    /**
     * Mapping to delete {id}-th product
     *
     * @param id
     * @return redirect
     */
    @RequestMapping("/product/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        productService.deleteById(id);

        notificationService.addInfoMessage("Продукт успешно удален");
        return "redirect:/products";
    }
}