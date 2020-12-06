package pl.sdacademy.majbaum.interfaces.comparable;

import java.util.Objects;

public class Person implements Comparable<Person> {
    private final String firstName;
    private final String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return firstName.equals(person.firstName) &&
                lastName.equals(person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public int compareTo(Person o) {
        /*
        final int lastNameResult = lastName.compareTo(o.lastName);
        if (lastNameResult != 0) {
            return lastNameResult;
        }

        return firstName.compareTo(o.firstName);
         */

        return PersonComparators.DEFAULT.compare(this, o);
    }
}
