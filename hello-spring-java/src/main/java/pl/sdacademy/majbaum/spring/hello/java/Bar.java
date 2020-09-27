package pl.sdacademy.majbaum.spring.hello.java;

public class Bar {
    private final String welcomeMessage;

    public Bar(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public String hello() {
        return welcomeMessage;
    }
}
