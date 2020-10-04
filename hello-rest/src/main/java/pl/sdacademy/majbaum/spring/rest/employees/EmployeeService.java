package pl.sdacademy.majbaum.spring.rest.employees;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    boolean isPresent(String code);
    List<Employee> getEmployees();
    Optional<Employee> getEmployee(String code);

    /**
     * Removes employee if present.
     * @param code employee code
     * @return {@code true} if employee has been removed, {@code false} otherwise.
     */
    boolean deleteEmployee(String code);

    /**
     * Adds employee if it doesn't exist already.
     * @param employee an employee to add
     * @return {@code true} if employee has been added, {@code false} otherwise.
     */
    boolean addEmployee(Employee employee);

    /**
     * Replaces employee if present.
     * @param employee an employee to replace
     * @return {@code true} if employee has been replaced, {@code false} otherwise.
     */
    boolean replaceEmployee(Employee employee);

}
