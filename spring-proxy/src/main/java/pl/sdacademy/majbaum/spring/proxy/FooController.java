package pl.sdacademy.majbaum.spring.proxy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class FooController {
    private final Integer myNumber;
    private final Bar bar;

    public FooController(
            @Value("${myapp.number}") Integer myNumber,
            Bar bar
    ) {
        this.myNumber = myNumber;
        this.bar = bar;
    }

    @GetMapping("/number")
    public Integer getMyNumber() {
        return myNumber;
    }

    @GetMapping("/foo")
    public String foo() {
        return bar.getNumber() + ", " + bar.getNumber();
    }
}
