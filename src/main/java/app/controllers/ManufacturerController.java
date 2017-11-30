package app.controllers;

import app.forms.ManufacturerForm;
import app.models.Manufacturer;
import app.models.Product;
import app.services.manufacturer.ManufacturerService;
import app.services.notification.NotificationService;
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
 * Controller for mapping manufacturer's pages
 */
@Controller
public class ManufacturerController {
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
     * Mapping to show all manufacturers and actions
     *
     * @param model
     * @return view
     */
    @RequestMapping("/manufacturers")
    public String index(Model model) {
        List<Manufacturer> manufacturers = manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);
        return "manufacturer/index";
    }

    /**
     * Mapping to show {id}-th manufacturer information
     *
     * @param id
     * @param model
     * @return view or redirect
     */
    @RequestMapping("/manufacturer/{id}")
    public String view(@PathVariable("id") Long id, Model model) {
        Manufacturer manufacturer = manufacturerService.findById(id);
        if (manufacturer == null) {
            notificationService.addErrorMessage("Cannot find manufacturer #" + id);
            return "redirect:/manufacturers";
        }
        model.addAttribute("manufacturer", manufacturer);
        return "manufacturer/view";
    }

    /**
     * Mapping to show all products by {id}-th manufacturer
     *
     * @param id
     * @param model
     * @return view or redirect
     */
    @RequestMapping("/manufacturer/{id}/products")
    public String products(@PathVariable("id") Long id, Model model) {
        Manufacturer manufacturer = manufacturerService.findById(id);
        List<Product> products = manufacturerService.findProducts(id);
        if (manufacturer == null) {
            notificationService.addErrorMessage("Cannot find manufacturer #" + id);
            return "redirect:/manufacturers";
        }
        model.addAttribute("manufacturer", manufacturer);
        model.addAttribute("products", products);
        return "manufacturer/productList";
    }

    /**
     * Mapping to show manufacturer's create form
     *
     * @param manufacturerForm
     * @return view
     */
    @RequestMapping("/manufacturer/create")
    public String create(ManufacturerForm manufacturerForm) {
        return "manufacturer/create";
    }

    /**
     * Mapping to validate and create new manufacturer
     *
     * @param manufacturerForm
     * @param bindingResult
     * @return view or redirect
     */
    @RequestMapping(value = "/manufacturer/create", method = RequestMethod.POST)
    public String create(@Valid ManufacturerForm manufacturerForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/manufacturer/create";
        }

        Manufacturer newManufacturer = new Manufacturer(manufacturerForm.getName(), manufacturerForm.getDescription(), manufacturerForm.getAddress());
        newManufacturer = manufacturerService.create(newManufacturer);

        notificationService.addInfoMessage("Производитель " + newManufacturer.getName() + " успешно добавлен");
        return "redirect:/manufacturers";
    }

    /**
     * Mapping to show {id}-th manufacturer edit form
     *
     * @param id
     * @param manufacturerForm
     * @return view or redirect
     */
    @RequestMapping("/manufacturer/{id}/edit")
    public String edit(@PathVariable("id") Long id, ManufacturerForm manufacturerForm) {
        Manufacturer manufacturer = manufacturerService.findById(id);
        if (manufacturer == null) {
            notificationService.addErrorMessage("Cannot find manufacturer #" + id);
            return "redirect:/manufacturers";
        }
        manufacturerForm.setId(manufacturer.getId());
        manufacturerForm.setName(manufacturer.getName());
        manufacturerForm.setDescription(manufacturer.getDescription());
        manufacturerForm.setAddress(manufacturer.getAddress());

        return "manufacturer/edit";
    }

    /**
     * Mapping to valid and update (save) {id}-th manufacturer
     *
     * @param id
     * @param manufacturerForm
     * @param bindingResult
     * @return view or redirect
     */
    @RequestMapping(value = "/manufacturer/{id}/edit", method = RequestMethod.POST)
    public String edit(@PathVariable("id") Long id, @Valid ManufacturerForm manufacturerForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/manufacturer/edit";
        }
        Manufacturer manufacturer = manufacturerService.findById(id);
        manufacturer.setName(manufacturerForm.getName());
        manufacturer.setDescription(manufacturerForm.getDescription());
        manufacturer.setAddress(manufacturerForm.getAddress());

        manufacturer = manufacturerService.edit(manufacturer);

        notificationService.addInfoMessage("Производитель " + manufacturer.getName() + " успешно обновлен");
        return "redirect:/manufacturers";
    }

    /**
     * Mapping to delete {id}-th manufacturer
     *
     * @param id
     * @return redirect
     */
    @RequestMapping("/manufacturer/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        manufacturerService.deleteById(id);

        notificationService.addInfoMessage("Производитель успешно удален");
        return "redirect:/manufacturers";
    }
}
