package pl.sdacademy.majbaum.spring.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class FooController {
    private final Foo foo;

    public FooController(Foo foo) {
        this.foo = foo;
    }

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String getFoo() {
        return "Hello";
    }

    @GetMapping("/foo")
    public ModelAndView getFooValue() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("foo");
        modelAndView.addObject("fooValue", foo.getVal());
        return modelAndView;
    }
}
