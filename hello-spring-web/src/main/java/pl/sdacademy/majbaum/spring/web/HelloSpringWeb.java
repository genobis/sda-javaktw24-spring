package pl.sdacademy.majbaum.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller @ResponseBody
public class HelloSpringWeb {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring Web!";
    }
}
