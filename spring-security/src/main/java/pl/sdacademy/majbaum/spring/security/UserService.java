package pl.sdacademy.majbaum.spring.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sdacademy.majbaum.spring.security.model.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public Optional<User> addUser(User user) {
        if (userRepository.existsById(user.getUserName())) {
            return Optional.empty();
        }

        final String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword); //zwykle lepiej nie zmieniać obiektu argumentu metody
        userRepository.save(user);
        return Optional.of(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
