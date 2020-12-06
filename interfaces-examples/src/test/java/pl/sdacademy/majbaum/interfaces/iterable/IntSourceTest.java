package pl.sdacademy.majbaum.interfaces.iterable;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class IntSourceTest {
    @Test
    void shouldFillListWithForeach() {
        //WHEN
        final IntSource intSource = new IntSource(1,2,3,4);
        final List<Integer> output = new LinkedList<>();

        for (final int i : intSource) {
            output.add(i);
        }

        //THEN
        assertThat(output).containsExactly(1,2,3,4);
    }
}