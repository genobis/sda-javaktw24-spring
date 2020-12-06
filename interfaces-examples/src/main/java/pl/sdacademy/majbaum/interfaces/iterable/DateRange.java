package pl.sdacademy.majbaum.interfaces.iterable;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DateRange implements Iterable<LocalDate> {
    private final LocalDate start;
    private final LocalDate end;

    public static DateRange of(LocalDate start, LocalDate end) {
        if ( start == null || end == null) {
            throw new IllegalArgumentException("start and end values must be non-null!");
        }

        if (end.isBefore(start)) {
            throw new IllegalArgumentException("end date must not be before start date!");
        }

        return new DateRange(start, end);
    }

    private DateRange(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Iterator<LocalDate> iterator() {
        return new DateIterator();
    }

    private class DateIterator implements Iterator<LocalDate> {
        private LocalDate nextDate = start;

        @Override
        public boolean hasNext() {
            return !nextDate.isAfter(end);
        }

        @Override
        public LocalDate next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            final LocalDate date = nextDate;
            nextDate = nextDate.plusDays(1);
            return date;
        }
    }
}
