package pl.sdacademy.majbaum.interfaces.closeable;

public class FooResource implements AutoCloseable {
    private boolean open = true;

    public void success() {
        System.out.println("Success!");
    }

    public void failure() {
        throw new RuntimeException("Oh no!");
    }

    public boolean isOpen() {
        return open;
    }

    public void close() {
        System.out.println("Closing...");
        open = false;
    }
}
