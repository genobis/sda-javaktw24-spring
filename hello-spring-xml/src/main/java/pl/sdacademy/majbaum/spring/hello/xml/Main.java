package pl.sdacademy.majbaum.spring.hello.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        final ApplicationContext applicationContext
                = new ClassPathXmlApplicationContext("FooBar.xml");
        final Foo foo = applicationContext.getBean(Foo.class);
        System.out.println(foo.getValue());
    }
}
