package pl.sdacademy.majbaum.spring.homework.security.domain.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.sdacademy.majbaum.spring.homework.security.model.User;
import pl.sdacademy.majbaum.spring.homework.security.model.enums.UserType;

import javax.annotation.PostConstruct;

@Component
public class UserInitializer {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    @Transactional
    public void addUsers() {
        addUser("admin", "Adam", "Admin", UserType.ADMIN);
        addUser("editor1", "Radosław", "Redaktor", UserType.EDITOR);
        addUser("editor2", "Romuald", "Redaktor", UserType.EDITOR);
        addUser("reader", "Czesław", "Czytelnik", UserType.READER);
    }

    private void addUser(
        final String userName,
        final String firstName,
        final String lastName,
        final UserType userType
    ) {
        if (userRepository.existsByUserName(userName)) {
            return;
        }

        final User user = new User();
        user.setUserName(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserType(userType);
        user.setPassword(passwordEncoder.encode("test"));
        userRepository.save(user);
    }
}
