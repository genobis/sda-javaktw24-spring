package pl.sdacademy.majbaum.spring.mavenexample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller @ResponseBody
public class HelloMaven {

    @GetMapping("/hello-maven")
    public String helloMaven() {
        return "Hello Maven!";
    }

}
