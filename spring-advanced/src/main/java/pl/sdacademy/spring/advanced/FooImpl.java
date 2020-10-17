package pl.sdacademy.spring.advanced;

import org.springframework.stereotype.Component;

//@Component
public class FooImpl implements Foo {

    @Override
    public int getValue() {
        return 1;
    }
}
