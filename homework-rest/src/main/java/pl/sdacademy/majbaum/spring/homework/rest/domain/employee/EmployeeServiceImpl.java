package pl.sdacademy.majbaum.spring.homework.rest.domain.employee;

import org.springframework.stereotype.Service;
import pl.sdacademy.majbaum.spring.homework.rest.domain.department.DepartmentRepository;
import pl.sdacademy.majbaum.spring.homework.rest.model.Department;
import pl.sdacademy.majbaum.spring.homework.rest.model.Employee;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> map = new LinkedHashMap<>();

    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

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

        //-----
        bindToDepartment(employee);
        //-----

        map.put(employee.getCode(), employee);
        return true;
    }

    @Override
    public boolean replaceEmployee(Employee employee) {
        if (!isPresent(employee.getCode())) {
            return false;
        }

        //-----
        bindToDepartment(employee);
        //-----

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

    //-----
    private void bindToDepartment(Employee employee) {
        final Optional<Department> existingDepartment = Optional.ofNullable(employee.getDepartment())
                .map(Department::getId)
                .flatMap(departmentRepository::findById);

        if (existingDepartment.isPresent()) {
            existingDepartment.get().getEmployees().add(employee);
        }
        else {
            final Department newDepartment = employee.getDepartment();
            newDepartment.getEmployees().add(employee);
            departmentRepository.save(newDepartment);
        }
    }
    //-----
}
