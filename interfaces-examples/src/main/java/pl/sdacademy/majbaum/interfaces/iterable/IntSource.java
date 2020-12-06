package pl.sdacademy.majbaum.interfaces.iterable;

import java.util.Iterator;
import java.util.List;

public class IntSource implements Iterable<Integer> {
    private final List<Integer> values;

    public IntSource(Integer... values) {
        this.values = List.of(values);
    }

    @Override
    public Iterator<Integer> iterator() {
        return values.iterator();
    }
}
