package pl.sdacademy.majbaum.spring.hello.java;

import org.springframework.stereotype.Component;

@Component
public class Foo {
    private final Bar bar;

    public Foo(Bar bar) {
        this.bar = bar;
    }

    public String getValue() {
        return bar.hello();
    }
}
