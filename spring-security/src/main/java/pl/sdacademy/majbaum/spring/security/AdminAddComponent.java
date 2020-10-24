package pl.sdacademy.majbaum.spring.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.sdacademy.majbaum.spring.security.model.User;

import javax.annotation.PostConstruct;

@Component
public class AdminAddComponent {
    private final UserRepository userRepository;

    public AdminAddComponent(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void createAdminIfNotPresent() {
        if (!userRepository.existsById("admin")) {
            final User user = new User();
            user.setUserName("admin");
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
        }
    }
}
