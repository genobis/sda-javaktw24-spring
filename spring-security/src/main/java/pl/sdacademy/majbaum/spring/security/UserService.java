package pl.sdacademy.majbaum.spring.security;

import org.springframework.stereotype.Service;
import pl.sdacademy.majbaum.spring.security.model.User;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
