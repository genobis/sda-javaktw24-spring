package pl.sdacademy.majbaum.spring.homework.data.domain.employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.sdacademy.majbaum.spring.homework.data.model.Employee;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
@Validated
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public EmployeeDto addEmployee(@RequestBody @Validated EmployeeDto employeeDto) {
        //Mapuje z DTO na klasę encyjną (instancja niezarządzana) i przekazuje do serwisu
        final Employee employee = employeeService.createEmployee(EmployeeMapper.map(employeeDto));

        //Mapuje z powrotem z klasy encyjnej na DTO i zwraca
        return EmployeeMapper.mapWithSkills(employee);
    }

    @GetMapping
    public Page<EmployeeDto> getEmployees(
            @RequestParam(required = false) Boolean retired, //Boolean - bo dopuszczamy null!
            @RequestParam(required = false) List<String> skills, //To mógłby też być Set<String>
            Pageable pageable
    ) {
        //Tu są dwie powiązane instrukcje (druga wywoływana na obiekcie zwróconym przez pierwszą)
        return employeeService.getEmployees(retired, skills, pageable) // wywołanie serwisu zweracającego pracowników
                .map(EmployeeMapper::mapWithoutSkills); // Page.map(...) przyjmuje jako argument  metodę mapującą
    }

    @GetMapping("/{id}")
    public EmployeeDto getEmployee(@PathVariable long id) {
        return employeeService.getEmployee(id)
                .map(EmployeeMapper::mapWithSkills) //Optional.map(...) przyjmuje jako argument metodę mapującą
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND)); //a jeśli nie ma - 404
    }

    @ExceptionHandler(SkillsNotFoundException.class)
    public void handleError(HttpServletResponse rsp, SkillsNotFoundException exception) throws IOException {
        //Przekierowanie do DOMYŚLNEGO mechanizmu obsługi błędów, pozwala na dodanie własnego komunikatu (jeśli server.error.include-message=always)
        rsp.sendError(
                HttpStatus.BAD_REQUEST.value(),
                //Komunikat: własny tekst + posortowane skille rozdzielone przecinkiem
                "Unknown skills: " + exception.getSkillsNotFound().stream().sorted().collect(Collectors.joining(", "))
        );
    }
}
