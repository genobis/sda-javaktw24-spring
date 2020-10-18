package pl.sdacademy.majbaum.spring.homework.rest.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.validation.constraints.NotBlank;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@JacksonXmlRootElement(localName = "department")
public class Department {
    @JsonView({Employee.class,Department.class})
    private Integer id;

    @NotBlank
    @JsonView({Employee.class,Department.class})
    private String name;

    @JacksonXmlElementWrapper(localName = "employees")
    @JacksonXmlProperty(localName = "employee")
    @JsonView(Department.class)
    private List<Employee> employees = new LinkedList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = Objects.requireNonNull(employees);
    }
}
