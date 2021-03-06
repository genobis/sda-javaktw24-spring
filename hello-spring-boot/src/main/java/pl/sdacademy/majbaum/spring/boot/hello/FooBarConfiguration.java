package pl.sdacademy.majbaum.spring.boot.hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class FooBarConfiguration {

    @Bean
    public Bar bar() {
        return new Bar("Hello Spring Java!");
    }
}
