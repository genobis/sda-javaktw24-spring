package pl.sdacademy.majbaum.spring.rest.employees;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        if (!employeeService.addEmployee(employee)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        return employee;
    }

    @PutMapping("/{code}")
    public ResponseEntity<Employee> addEmployee(
            @PathVariable String code,
            @RequestBody Employee employee
    ) {
        if (!code.equals(employee.getCode())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Employee code not consistent with path variable"
            );
        }

        if (employeeService.isPresent(code)) {
            employeeService.replaceEmployee(employee);
            return ResponseEntity.ok(employee);
        }
        else {
            employeeService.addEmployee(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(employee);
        }
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/{code}")
    public Employee getEmployee(@PathVariable String code) {
        /*
        final Optional<Employee> employeeOptional = employeeService.getEmployee(code);
        if (employeeOptional.isPresent()) {
            return employeeOptional.get();
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }*/

        return employeeService.getEmployee(code).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable String code) {
        if(!employeeService.deleteEmployee(code)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
