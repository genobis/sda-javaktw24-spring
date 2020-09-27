package pl.sdacademy.majbaum.spring.hello.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FooBarConfiguration {

    @Bean
    public Bar bar() {
        return new Bar("Hello Spring Java!");
    }

    @Bean
    public Foo foo(Bar bar) {
        return new Foo(bar);
    }
}
