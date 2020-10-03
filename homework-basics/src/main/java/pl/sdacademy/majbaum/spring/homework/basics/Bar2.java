package pl.sdacademy.majbaum.spring.homework.basics;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//ładowane przez skanowanie, prototype
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Bar2 {
    private final Foo1 foo1;
    private final Foo2 foo2;

    public Bar2(Foo1 foo1, Foo2 foo2) {
        this.foo1 = foo1;
        this.foo2 = foo2;
    }

    public int getR1() { return foo1.getR(); }

    public int getR2() { return foo2.getR(); }
}
