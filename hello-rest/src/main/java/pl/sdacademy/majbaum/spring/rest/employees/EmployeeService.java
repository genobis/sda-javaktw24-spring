package pl.sdacademy.majbaum.spring.rest.employees;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getEmployees();
    Optional<Employee> getEmployee(String code);
    boolean deleteEmployee(String code);
    void addEmployee(Employee employee);
}
