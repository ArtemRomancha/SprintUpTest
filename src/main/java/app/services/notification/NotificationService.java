package app.services.notification;

/**
 * Interface for notification
 */
public interface NotificationService {
    void addInfoMessage(String msg);
    void addErrorMessage(String msg);
}
