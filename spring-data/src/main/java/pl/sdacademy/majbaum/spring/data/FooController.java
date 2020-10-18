package pl.sdacademy.majbaum.spring.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.majbaum.spring.data.model.Foo;

@RestController
@RequestMapping("/foos")
public class FooController {
    private final FooService fooService;

    public FooController(FooService fooService) {
        this.fooService = fooService;
    }

    @GetMapping
    public Page<Foo> getFoos(@RequestParam(required = false) String query, Pageable pageable) {
        return fooService.getFoos(query, pageable);
    }

    @PostMapping
    public Foo addFoo(@RequestBody Foo foo) {
        return fooService.addFoo(foo);
    }

    @PatchMapping("/{id}")
    public Foo changeFoo(@PathVariable long id, @RequestBody Foo foo) {
        final String newName = foo.getName();
        return fooService.changeName(id, newName);
    }
}
