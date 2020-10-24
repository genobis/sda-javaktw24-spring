package pl.sdacademy.majbaum.spring.security;

import org.springframework.stereotype.Component;
import pl.sdacademy.majbaum.spring.security.model.User;

import javax.annotation.PostConstruct;

@Component
public class AdminAddComponent {
    private final UserService userService;

    public AdminAddComponent(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void createAdminIfNotPresent() {
        final User user = new User();
        user.setUserName("admin");
        user.setPassword("admin");
        user.setRole("ROLE_ADMIN");
        userService.addUser(user);
    }
}
