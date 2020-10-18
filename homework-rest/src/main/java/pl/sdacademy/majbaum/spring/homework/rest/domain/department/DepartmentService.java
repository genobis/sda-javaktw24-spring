package pl.sdacademy.majbaum.spring.homework.rest.domain.department;

import org.springframework.stereotype.Service;
import pl.sdacademy.majbaum.spring.homework.rest.domain.employee.EmployeeService;
import pl.sdacademy.majbaum.spring.homework.rest.model.Department;
import pl.sdacademy.majbaum.spring.homework.rest.model.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeService employeeService;

    public DepartmentService(DepartmentRepository departmentRepository, EmployeeService employeeService) {
        this.departmentRepository = departmentRepository;
        this.employeeService = employeeService;
    }

    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartment(int id) {
        return departmentRepository.findById(id);
    }

    public Department addDepartment(Department department) {
        final boolean anyEmployeeAlreadyExists = department.getEmployees().stream()
                .map(Employee::getCode)
                .anyMatch(employeeService::isPresent);

        if (anyEmployeeAlreadyExists) {
            throw new IllegalArgumentException("Some employees already exist");
        }

        final Department savedDepartment = departmentRepository.save(department);

        Optional.ofNullable(department.getEmployees()).stream()
                .flatMap(Collection::stream)
                .peek(e -> e.setDepartment(savedDepartment))
                .forEach(employeeService::addEmployee);

        return savedDepartment;
    }
}
