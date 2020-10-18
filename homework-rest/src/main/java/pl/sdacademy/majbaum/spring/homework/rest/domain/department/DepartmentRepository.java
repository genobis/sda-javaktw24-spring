package pl.sdacademy.majbaum.spring.homework.rest.domain.department;

import org.springframework.stereotype.Service;
import pl.sdacademy.majbaum.spring.homework.rest.model.Department;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service //To jeszcze NIE jest prawdziwe Repository! Ale symboliczne wydzielenie warstwy danych
public class DepartmentRepository {
    private final AtomicInteger idSequence = new AtomicInteger();
    private final Map<Integer,Department>  map = new ConcurrentHashMap<>();

    public Department save(Department department) {
        if (department.getId() != null) {
            throw new IllegalArgumentException("department id must not be set");
        }

        department.setId(idSequence.incrementAndGet());
        map.put(department.getId(), department);
        return department;
    }

    public Optional<Department> findById(int id) {
        return Optional.ofNullable(map.get(id));
    }

    public List<Department> findAll() {
        return map.values().stream()
                .sorted(Comparator.comparing(Department::getName))
                .collect(Collectors.toList());
    }


}
