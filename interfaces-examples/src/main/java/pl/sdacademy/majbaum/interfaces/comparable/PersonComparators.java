package pl.sdacademy.majbaum.interfaces.comparable;

import java.util.Comparator;

public class PersonComparators {
    private PersonComparators() { }

    public static final Comparator<Person> DEFAULT = Comparator
            .comparing(Person::getLastName)
            .thenComparing(Person::getFirstName);
}
