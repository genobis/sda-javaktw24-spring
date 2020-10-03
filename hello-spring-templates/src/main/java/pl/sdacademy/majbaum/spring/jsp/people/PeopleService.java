package pl.sdacademy.majbaum.spring.jsp.people;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class PeopleService {
    private final List<Person> people = new LinkedList<>();

    public void addPerson(Person person) {
        people.add(person);
    }

    public List<Person> getPeople() {
        return people;
    }
}
