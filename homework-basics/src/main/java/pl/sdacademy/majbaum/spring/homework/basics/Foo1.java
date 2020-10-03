package pl.sdacademy.majbaum.spring.homework.basics;

//ładowane przez konfigurację, singleton
public class Foo1 {
    private final int random;

    public Foo1(int random) { this.random = random; }

    public int getR() { return random; }
}
