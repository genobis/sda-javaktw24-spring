package pl.sdacademy.majbaum.spring.hello.xml;

public class Foo {
    private final Bar bar;

    public Foo(Bar bar) {
        this.bar = bar;
    }

    public String getValue() {
        return bar.hello();
    }
}
