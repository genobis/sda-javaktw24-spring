package pl.sdacademy.majbaum.spring.hello.java;

public class Foo {
    private final Bar bar;

    public Foo(Bar bar) {
        this.bar = bar;
    }

    public String getValue() {
        return bar.hello();
    }
}
