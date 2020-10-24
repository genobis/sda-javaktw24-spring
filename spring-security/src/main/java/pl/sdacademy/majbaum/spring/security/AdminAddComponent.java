package pl.sdacademy.majbaum.spring.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.sdacademy.majbaum.spring.security.model.User;

import javax.annotation.PostConstruct;

@Component
public class AdminAddComponent {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminAddComponent(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void createAdminIfNotPresent() {
        if (!userRepository.existsById("admin")) {
            final User user = new User();
            user.setUserName("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            userRepository.save(user);
        }
    }
}
