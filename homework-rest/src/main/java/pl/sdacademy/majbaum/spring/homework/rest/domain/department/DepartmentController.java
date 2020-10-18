package pl.sdacademy.majbaum.spring.homework.rest.domain.department;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.sdacademy.majbaum.spring.homework.rest.model.Department;
import pl.sdacademy.majbaum.spring.homework.rest.model.Employee;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @JsonView(Department.class)
    @GetMapping
    public List<Department> getDepartments() {
        return departmentService.getDepartments();
    }

    @JsonView(Department.class)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Department addDepartment(Department department) {
        return departmentService.addDepartment(department);
    }


    @JsonView(Department.class)
    @GetMapping("/{id}")
    public Department getById(@PathVariable int id) {
        return departmentService.getDepartment(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @JsonView(Employee.class)
    @GetMapping("/{id}/employees")
    public List<Employee> getEmployees(@PathVariable int id) {
        return departmentService.getDepartment(id).map(Department::getEmployees)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
