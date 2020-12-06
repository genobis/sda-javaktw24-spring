package pl.sdacademy.majbaum.interfaces.iterable;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class IterableTest {

    @Test
    void shouldFillListWithForeach() {
        //GIVEN
        final List<Integer> input = List.of(1,2,3,4);

        //WHEN
        final List<Integer> output = new LinkedList<>();

        for (final int i : input) {
            output.add(i);
        }

        //THEN
        assertThat(output).containsExactly(1,2,3,4);
    }

    @Test
    void shouldFillListWithIterator() {
        //GIVEN
        final List<Integer> input = List.of(1,2,3,4);

        //WHEN
        final List<Integer> output = new LinkedList<>();

        final Iterator<Integer> iterator = input.iterator();

        while (iterator.hasNext()) {
            final int value = iterator.next();
            output.add(value);
        }

        //THEN
        assertThat(output).containsExactly(1,2,3,4);
    }
}
