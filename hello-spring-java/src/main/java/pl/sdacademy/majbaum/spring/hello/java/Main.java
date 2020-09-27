package pl.sdacademy.majbaum.spring.hello.java;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        final ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(FooBarConfiguration.class);
        final Foo foo = applicationContext.getBean(Foo.class);
        System.out.println(foo.getValue());
    }
}
