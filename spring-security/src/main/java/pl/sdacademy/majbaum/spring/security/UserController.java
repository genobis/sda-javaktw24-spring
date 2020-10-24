package pl.sdacademy.majbaum.spring.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.sdacademy.majbaum.spring.security.model.User;

import java.util.List;

@RequestMapping("/users")
@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public void getUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
    }

    @ResponseBody
    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.CONFLICT));
    }
}
