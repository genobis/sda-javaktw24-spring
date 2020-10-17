package pl.sdacademy.spring.advanced;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfiguration {
    @Bean
    public Foo foo() {
        return new FooImpl();
    }

    @Primary
    @Bean("differentFoo")
    public Foo otherFoo() {
        return new OtherFooImpl();
    }
}
