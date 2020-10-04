package pl.sdacademy.majbaum.spring.rest.employees;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String,Employee> map = new LinkedHashMap<>();

    @Override
    public boolean isPresent(String code) {
        return map.containsKey(code);
    }

    @Override
    public List<Employee> getEmployees(LocalDate dateFrom, LocalDate dateTo) {
        final LocalDate sanitizedDateFrom = Optional.ofNullable(dateFrom).orElse(LocalDate.MIN);
        final LocalDate sanitizedDateTo = Optional.ofNullable(dateTo).orElse(LocalDate.now());

        if (sanitizedDateFrom.isAfter(sanitizedDateTo)) {
            throw new IllegalArgumentException("dateFrom MUST NOT be after dateTo");
        }

        return map.values().stream()
                .filter(employee ->
                        employeeFilter(employee, sanitizedDateFrom, sanitizedDateTo)
                )
                .collect(Collectors.toList());
    }

    private boolean employeeFilter(Employee employee, LocalDate dateFrom, LocalDate dateTo) {
        final LocalDate birthDate = employee.getBirthDate();
        if (birthDate == null) {
            return true;
        }

        return birthDate.isAfter(dateFrom) &&  birthDate.isBefore(dateTo);
    }

    @Override
    public boolean addEmployee(Employee employee) {
        if (isPresent(employee.getCode())) {
            return false;
        }

        map.put(employee.getCode(), employee);
        return true;
    }

    @Override
    public boolean replaceEmployee(Employee employee) {
        if (!isPresent(employee.getCode())) {
            return false;
        }

        map.put(employee.getCode(), employee);
        return true;
    }

    @Override
    public Optional<Employee> getEmployee(String code) {
        return Optional.ofNullable(map.get(code));
    }

    @Override
    public boolean deleteEmployee(String code) {
        return map.remove(code) != null;
    }
}
