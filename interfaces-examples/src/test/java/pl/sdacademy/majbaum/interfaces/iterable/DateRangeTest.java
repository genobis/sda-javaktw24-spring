package pl.sdacademy.majbaum.interfaces.iterable;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DateRangeTest {

    @Test
    void shouldReturnExpectedDates() {
        //WHEN
        final DateRange dateRange = DateRange.of(
                LocalDate.of(2021, 2, 27),
                LocalDate.of(2021, 3, 2)
        );

        final List<LocalDate> result = new LinkedList<>();

        for (final LocalDate date : dateRange) {
           result.add(date);
        }

        //dateRange.forEach(result::add);

        //THEN
        assertThat(result).containsExactly(
          LocalDate.of(2021, 2, 27),
          LocalDate.of(2021, 2, 28),
          LocalDate.of(2021, 3, 1),
          LocalDate.of(2021, 3, 2)
        );
    }
}