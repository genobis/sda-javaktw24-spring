package pl.sdacademy.majbaum.spring.homework.basics;

//ładowane przez konfigurację, prototype
public class Foo2 {
    private final int random;

    public Foo2(int random) { this.random = random; }

    public int getR() { return random; }
}
