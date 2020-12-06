package pl.sdacademy.majbaum.interfaces.comparable;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PersonTest {

    @Test
    void shouldSortNaturally() {
        //GIVEN
        final List<Person> people = new ArrayList<>(peopleFixture());

        //WHEN
        Collections.sort(people);

        //THEN
        assertThat(people).containsExactlyElementsOf(lastNameSortedPeopleFixture());
    }

    @Test
    void shouldSortByFirstName() {
        //GIVEN
        final List<Person> people = new ArrayList<>(peopleFixture());

        //WHEN
        /*final Comparator<Person> firstNameComparator = (o1, o2) -> {
            final int firstNameResult = o1.getFirstName().compareTo(o2.getFirstName());
            if (firstNameResult != 0) {
                return firstNameResult;
            }

            return o1.getLastName().compareTo(o2.getLastName());
        };*/

        final Comparator<Person> firstNameComparator = Comparator
                .comparing(Person::getFirstName)
                .thenComparing(Person::getLastName);

        //Collections.sort(people, firstNameComparator);
        people.sort(firstNameComparator);

        //THEN
        assertThat(people).containsExactlyElementsOf(firstNameSortedPeopleFixture());
    }

    private List<Person> peopleFixture() {
        return List.of(
                new Person("Lech", "Wałęsa"),
                new Person("Aleksander", "Kwaśniewski"),
                new Person("Lech", "Kaczyński"),
                new Person("Bronisław", "Komorowski"),
                new Person("Andrzej", "Duda"),
                new Person("Jarosław", "Kaczyński")
        );
    }

    private List<Person> lastNameSortedPeopleFixture() {
        return List.of(
                new Person("Andrzej", "Duda"),
                new Person("Jarosław", "Kaczyński"),
                new Person("Lech", "Kaczyński"),
                new Person("Bronisław", "Komorowski"),
                new Person("Aleksander", "Kwaśniewski"),
                new Person("Lech", "Wałęsa")
        );
    }


    private List<Person> firstNameSortedPeopleFixture() {
        return List.of(
                new Person("Aleksander", "Kwaśniewski"),
                new Person("Andrzej", "Duda"),
                new Person("Bronisław", "Komorowski"),
                new Person("Jarosław", "Kaczyński"),
                new Person("Lech", "Kaczyński"),
                new Person("Lech", "Wałęsa")
        );
    }
}
