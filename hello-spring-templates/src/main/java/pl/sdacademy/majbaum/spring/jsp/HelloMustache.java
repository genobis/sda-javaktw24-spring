package pl.sdacademy.majbaum.spring.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class HelloMustache {
    @GetMapping("/hello-mustache")
    public String helloJsp(Model model) {
        model.addAttribute("firstName", "Tomek");
        model.addAttribute("lastName", "Majbaum");
        model.addAttribute(
                "birthDate",
                LocalDate.of(1983, 12, 13)
        );

        return "hello";
    }
}
