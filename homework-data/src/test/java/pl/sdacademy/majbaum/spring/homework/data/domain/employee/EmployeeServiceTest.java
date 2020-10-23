package pl.sdacademy.majbaum.spring.homework.data.domain.employee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import pl.sdacademy.majbaum.spring.homework.data.domain.skill.SkillRepository;
import pl.sdacademy.majbaum.spring.homework.data.model.Employee;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private SkillRepository skillRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        final Clock clock = Clock.fixed(Instant.parse("2019-07-17T15:15:22Z"), ZoneId.of("UTC"));
        employeeService = new EmployeeService(skillRepository, employeeRepository, clock);
    }

    @Test
    void shouldQueryForAll() {
        //GIVEN
        final Page<Employee> expectedResult = emptyPageFixture();
        given(employeeRepository.findByBirthDateBetween(
            eq(LocalDate.of(1800,1,1)),
            eq(LocalDate.of(2019,7,17)),
            any()
        )).willReturn(expectedResult);

        //WHEN
        var result = employeeService.getEmployees(null, null, null);

        //THEN
        assertSame(expectedResult, result);
    }

    @Test
    void shouldQueryForRetired() {
        //GIVEN
        final Page<Employee> expectedResult = emptyPageFixture();
        given(employeeRepository.findByBirthDateBetween(
            eq(LocalDate.of(1800,1,1)),
            eq(LocalDate.of(1959,7,17)),
            any()
        )).willReturn(expectedResult);

        //WHEN
        var result = employeeService.getEmployees(true, null, null);

        //THEN
        assertSame(expectedResult, result);
    }

    @Test
    void shouldQueryForNotRetired() {
        //GIVEN
        final Page<Employee> expectedResult = emptyPageFixture();
        given(employeeRepository.findByBirthDateBetween(
            eq(LocalDate.of(1959,7,18)),
            eq(LocalDate.of(2019,7,17)),
            any()
        )).willReturn(expectedResult);

        //WHEN
        var result = employeeService.getEmployees(false, null, null);

        //THEN
        assertSame(expectedResult, result);
    }

    private Page<Employee> emptyPageFixture() {
        return new PageImpl<>(Collections.emptyList());
    }

    //Uwaga: test niekompletny!
}
