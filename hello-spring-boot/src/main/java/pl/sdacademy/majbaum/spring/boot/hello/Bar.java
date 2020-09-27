package pl.sdacademy.majbaum.spring.boot.hello;

public class Bar {
    private final String welcomeMessage;

    public Bar(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public String hello() {
        return welcomeMessage;
    }
}
