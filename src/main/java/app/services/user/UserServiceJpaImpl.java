package app.services.user;

import app.models.Role;
import app.models.User;
import app.repositories.RoleRepository;
import app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * JPA implementation of userService
 */
@Service
public class UserServiceJpaImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<User> findAll() {
        return this.userRepo.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepo.findByEmail(email);
    }

    @Override
    public User create(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepo.findByRole("USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        return this.userRepo.save(user);
    }

    @Override
    public User edit(User user) {
        return this.userRepo.save(user);
    }

    @Override
    public void deleteById(Long id) {
        this.userRepo.delete(id);
    }
}
