package pl.sdacademy.majbaum.spring.rest.employees;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.List;

@Validated //Wymagane by działały adnotacje walidujące parametry metod!
@RequestMapping("/employees") //Ścieżkę bazową wszystkich metod kontrolera można ustawić w ten sposób
@RestController //@Controller + @ResponseBody
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //Najprostsze podejście: adnotacja @ResponseStatus na własny status odpowiedzi, wyjątek w przypadku błędu
    @ResponseStatus(HttpStatus.CREATED) //Przy poprawnym wykonaniu, status CREATED (201)
    @PostMapping //HTTP POST, ścieżka jak z @RequestMapping
    public Employee addEmployee(@RequestBody @Validated Employee employee) { //@RequestBody - obiekt przesłany w ciele żądania; @Validated - wymaga walidacji
        if (!employeeService.addEmployee(employee)) { //Zakładamy, że zwraca false gdy już istnieje taki pracownik; innym sposobem mógłby być własny wyjątek
            throw new ResponseStatusException(HttpStatus.CONFLICT); //CONFLICT (409)
        }

        return employee;
    }

    //Status odpowiedzi wg ResponseEntity
    @PutMapping("/{code}") //HTTP PUT, ścieżka /employees/{code} gdzie {code} to identyfikator pracownika
    public ResponseEntity<Employee> addEmployee(
            @PathVariable @NotBlank String code, //@PathVariable - zmienna pobrana ze ścieżki; @NotBlank - nie może być null, puste, ani same białe znaki - tutaj nadmiarowe
            @RequestBody @Validated Employee employee //@RequestBody - obiekt przesłany w ciele żądania; @Validated - wymaga walidacji
    ) {
        if (!code.equals(employee.getCode())) { //Jest możliwe, że kod w ścieżce nie będzie się zgadzał z obiektem; trzeba taki przypadek samodzielnie obsłużyć
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, //BAD REQUEST (400) - uniwersalny kod błędu dla nieprawidłowych żądań (niezależnie czy chodzi o ścieżkę
                    "Employee code not consistent with path variable" //Żeby komunikat był widoczny dla klienta, wymagana własna obsługa błędu lub ustawienie server.error.include-message=always (obecnie niezalecane - możliwe ujawnienie szczegółów implementacji)
            );
        }

        //W rzeczywistości niezbyt często stosowane, ale możliwe jest zwracanie różnych poprawnych statusów odpowiedzi zależnie od sposobu obsługi żądania
        if (employeeService.isPresent(code)) {
            employeeService.replaceEmployee(employee);
            return ResponseEntity.ok(employee);
        }
        else {
            employeeService.addEmployee(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(employee);
        }
    }

    //Jeśli nie ma żadnej dodatkowej informacji o statusie odpowiedzi, będzie to OK (200)
    @GetMapping //HTTP GET, ścieżka /employees
    public List<Employee> getEmployees(
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) //Konieczne wskazanie formatu daty aby możliwa była deserializacja; można ustawić globalnie: https://www.baeldung.com/spring-date-parameters
        @RequestParam(name = "date-from", required = false) //Nazwa parametru możne być inna od pola; required=false oznacza, że może tego nie być, wtedy będzie null
        @PastOrPresent //Data w przeszłości lub bieżąca. Null DOZWOLONY (gdyby nie był, trzeba dodatkowo @NotNull)
        LocalDate dateFrom,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        @RequestParam(name = "date-to", required = false)
        @PastOrPresent
        LocalDate dateTo
    ) {
        return employeeService.getEmployees(dateFrom, dateTo);
    }

    @GetMapping("/{code}") //HTTP GET, ścieżka /employees/{code} gdzie {code} to identyfikator pracownika
    public Employee getEmployee(@PathVariable @NotBlank String code) {
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

    //Zwyczajowo DELETE nie zwraca odpowiedzi, prawidłowy status w takiej sytuacji to NO CONTENT (204), ale trzeba ustawić bezpośrednio
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{code}") //HTTP DELETE ścieżka /employees/{code} gdzie {code} to identyfikator pracownika
    public void deleteEmployee(@PathVariable @NotBlank String code) {
        if(!employeeService.deleteEmployee(code)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
