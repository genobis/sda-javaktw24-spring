package pl.sdacademy.majbaum.spring.rest.employees;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String,Employee> map = new LinkedHashMap<>();

    @Override
    public List<Employee> getEmployees() {
        return new ArrayList<>(map.values());
    }

    @Override
    public void addEmployee(Employee employee) {
        map.put(employee.getCode(), employee);
    }

    @Override
    public Optional<Employee> getEmployee(String code) {
        return Optional.empty();
    }

    @Override
    public boolean deleteEmployee(String code) {
        return false;
    }
}
