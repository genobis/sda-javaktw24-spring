package pl.sdacademy.majbaum.interfaces.iterable;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

class FibonacciTest {

    @Test
    void shouldReturnExpectedSequence() {
        //WHEN
        final List<Long> values = new LinkedList<>();

        final Iterator<Long> fibonacciIterator = Fibonacci.getInstance().iterator();

        for (int i = 0; i < 10; i++) {
            values.add(fibonacciIterator.next());
        }

        //THEN
        assertThat(values).containsExactly(
                0L, 1L, 1L, 2L, 3L,
                5L, 8L, 13L, 21L, 34L
        );
    }

    @Test
    void shouldReturnExpectedSequenceWithSpliterator() {
        //WHEN
        final List<Long> values = StreamSupport
                .stream(Fibonacci.getInstance().spliterator(), false)
                //.skip(5)
                .limit(10)
                .collect(Collectors.toList());

        //THEN
        assertThat(values).containsExactly(
                0L, 1L, 1L, 2L, 3L,
                5L, 8L, 13L, 21L, 34L
        );
    }
}