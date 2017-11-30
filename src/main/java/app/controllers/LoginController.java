package app.controllers;

import app.models.User;
import app.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Class for mapping login and registration
 */
@Controller
public class LoginController {

    /**
     * Service to access user data
     */
    @Autowired
    private UserService userService;

    /**
     * Mapping to show login page
     *
     * @return modelAndView
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/login");
        return modelAndView;
    }

    /**
     * Mapping to show registration page
     *
     * @param model
     * @return view
     */
    @RequestMapping(value = "/registration")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user/registration";
    }

    /**
     * Mapping to check validation registration form and register user
     *
     * @param user
     * @param bindingResult
     * @return view
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createNewUser(@Valid User user, BindingResult bindingResult) {
        User userExists = userService.findByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "Пользователь с таким адресом электронной почты уже существует");
        }
        if (bindingResult.hasErrors()) {
            return "user/registration";
        } else {
            userService.create(user);
            return "redirect:/";
        }
    }
}