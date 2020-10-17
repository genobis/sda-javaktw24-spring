package pl.sdacademy.majbaum.spring.data;

import org.springframework.web.bind.annotation.*;
import pl.sdacademy.majbaum.spring.data.model.Foo;

import java.util.List;

@RestController
@RequestMapping("/foos")
public class FooController {
    private final FooService fooService;

    public FooController(FooService fooService) {
        this.fooService = fooService;
    }

    @GetMapping
    public List<Foo> getFoos() {
        return fooService.getFoos();
    }

    @PostMapping
    public Foo addFoo(@RequestBody Foo foo) {
        return fooService.addFoo(foo);
    }
}
