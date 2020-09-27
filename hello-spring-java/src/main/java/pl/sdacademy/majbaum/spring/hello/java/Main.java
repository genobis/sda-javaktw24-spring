package pl.sdacademy.majbaum.spring.hello.java;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        final ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(FooBarConfiguration.class);
        final Foo foo1 = applicationContext.getBean(Foo.class);
        final Foo foo2 = applicationContext.getBean(Foo.class);

        final Bar bar1 = applicationContext.getBean(Bar.class);
        final Bar bar2 = applicationContext.getBean(Bar.class);
        System.out.println(foo1.getValue());
    }
}
