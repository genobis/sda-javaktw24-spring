package pl.sdacademy.majbaum.interfaces.iterable;

import java.util.Iterator;

public class Fibonacci implements Iterable<Long> {
    private static final Fibonacci INSTANCE = new Fibonacci();

    private Fibonacci() {}

    public static Fibonacci getInstance() {
        return INSTANCE;
    }

    @Override
    public Iterator<Long> iterator() {
        return new FibonacciIterator();
    }

    private static class FibonacciIterator implements Iterator<Long> {
        private long current = 0;
        private long next = 1;

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Long next() {
            final long value = current;
            final long sum = current + next;
            current = next;
            next = sum;

            return value;
        }
    }
}
