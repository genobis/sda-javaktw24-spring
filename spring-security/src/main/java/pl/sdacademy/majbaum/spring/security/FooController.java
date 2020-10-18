package pl.sdacademy.majbaum.spring.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class FooController {

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String getFoo() {
        return "Hello";
    }
}
