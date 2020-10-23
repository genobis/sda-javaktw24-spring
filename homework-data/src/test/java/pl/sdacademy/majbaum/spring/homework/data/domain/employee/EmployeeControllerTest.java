package pl.sdacademy.majbaum.spring.homework.data.domain.employee;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import pl.sdacademy.majbaum.spring.homework.data.model.Employee;
import pl.sdacademy.majbaum.spring.homework.data.model.Skill;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeControllerTest.class);

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getEmployees() throws Exception {
        //GIVEN
        given(employeeService.getEmployees(
                eq(true),
                eq(List.of("java", "sql")),
                any()
        )).willReturn(new PageImpl<>(List.of(employeeFixture())));

        //WHEN
        final ResultActions resultActions = mockMvc.perform(
                get("/employees")
                        .queryParam("retired", "true")
                        .queryParam("skills", "java", "sql")
                        .accept(MediaType.APPLICATION_JSON)
        );

        //THEN
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].firstName").value("Jan"))
                .andExpect(jsonPath("$.content[0].lastName").value("Kowalski"))
                .andExpect(jsonPath("$.content[0].birthDate").value("1992-11-05"))
                .andExpect(jsonPath("$.content[0].skills").doesNotExist());

        //wyłącznie dla prezentacji
        final MvcResult mvcResult = resultActions.andReturn();
        logger.info(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void getEmployee() throws Exception {
        //GIVEN
        given(employeeService.getEmployee(1)).willReturn(Optional.of(employeeFixture()));

        //WHEN
        final ResultActions resultActions = mockMvc.perform(
                get("/employees/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON)
        );

        //THEN
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Jan"))
                .andExpect(jsonPath("$.lastName").value("Kowalski"))
                .andExpect(jsonPath("$.birthDate").value("1992-11-05"))
                .andExpect(jsonPath("$.skills").isArray())
                .andExpect(jsonPath("$.skills.length()").value(2))
                .andExpect(jsonPath("$.skills[0]").value("java"))
                .andExpect(jsonPath("$.skills[1]").value("sql"));

        //wyłącznie dla prezentacji
        final MvcResult mvcResult = resultActions.andReturn();
        logger.info(mvcResult.getResponse().getContentAsString());
    }

    private Employee employeeFixture() {
        final Skill skillSql = new Skill();
        skillSql.setName("sql");

        final Skill skillJava = new Skill();
        skillJava.setName("java");

        final Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("Jan");
        employee.setLastName("Kowalski");
        employee.setBirthDate(LocalDate.of(1992, 11, 5));
        employee.setSkills(Set.of(skillSql, skillJava));

        return employee;
    }
}
