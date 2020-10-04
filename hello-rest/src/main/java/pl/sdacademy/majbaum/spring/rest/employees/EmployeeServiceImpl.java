package pl.sdacademy.majbaum.spring.rest.employees;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String,Employee> map = new LinkedHashMap<>();

    @Override
    public boolean isPresent(String code) {
        return map.containsKey(code);
    }

    @Override
    public List<Employee> getEmployees() {
        return new ArrayList<>(map.values());
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
