package pl.sdacademy.majbaum.spring.homework.security.domain.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    private final String welcomePage;

    public MainController(@Value("${homework.security.welcome-page}") String welcomePage) {
        this.welcomePage = welcomePage;
    }

    @GetMapping
    public String getWelcomePage() {
        return "redirect:"+welcomePage;
    }
}
