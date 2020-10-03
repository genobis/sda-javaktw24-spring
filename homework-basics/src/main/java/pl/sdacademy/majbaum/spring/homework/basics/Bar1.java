package pl.sdacademy.majbaum.spring.homework.basics;

import org.springframework.stereotype.Component;

//ładowane przez skanowanie, singleton
@Component
public class Bar1 {
    private final Foo1 foo1;
    private final Foo2 foo2;

    public Bar1(Foo1 foo1, Foo2 foo2) {
        this.foo1 = foo1;
        this.foo2 = foo2;
    }

    public int getR1() { return foo1.getR(); }

    public int getR2() { return foo2.getR(); }
}
