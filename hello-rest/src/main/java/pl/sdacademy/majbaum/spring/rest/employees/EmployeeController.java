package pl.sdacademy.majbaum.spring.rest.employees;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequestMapping("/employees")
@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PutMapping("/{code}")
    public Employee addEmployee(@PathVariable String code, @RequestBody Employee employee) {
        if (!code.equals(employee.getCode())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Employee code not consistent with path variable"
            );
        }

        employeeService.addEmployee(employee);
        return employee;
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }
}
