package app.controllers;

import app.services.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller to generate notifications
 */
@Controller
public class NotifyController {

    /**
     * Service to store notifications
     */
    @Autowired
    private NotificationService notificationService;

    /**
     * Mapping to add error notification
     */
    @RequestMapping("/generateErrorNotify")
    public void error() {
        notificationService.addErrorMessage("Продукт успешно удален");
    }

    /**
     * Mapping to add info notification
     */
    @RequestMapping("/generateInfoNotify")
    public void info() {
        notificationService.addInfoMessage("Продукт успешно удален");

    }
}
