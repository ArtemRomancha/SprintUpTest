package app.services.user;

import app.models.User;
import java.util.List;

/**
 * Interface for manufacturer data service
 */
public interface UserService {
    List<User> findAll();
    User findByEmail(String email);
    User create(User user);
    User edit(User user);
    void deleteById(Long id);
}
