package app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SimpleRestController {
    @RequestMapping(value = "/rest")
    public String rest() {
        return "test/index";
    }
}
