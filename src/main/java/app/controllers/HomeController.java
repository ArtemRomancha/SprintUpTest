package app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Class for mapping home
 */
@Controller
public class HomeController {

    /**
     * Show home page
     * @return view
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/test")
    public String test() {
        return "test";
    }
}