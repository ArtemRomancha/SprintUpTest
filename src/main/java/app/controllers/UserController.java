package app.controllers;

import app.models.User;
import app.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Controller for mapping user's pages
 */
@Controller
public class UserController {
    /**
     * Service to access user data
     */
    @Autowired
    private UserService userService;

    /**
     * Mapping to show all users
     *
     * @param model
     * @return view
     */
    @RequestMapping("/users")
    public String index(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user/index";
    }
}

