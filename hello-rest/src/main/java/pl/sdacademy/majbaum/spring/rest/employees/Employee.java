package pl.sdacademy.majbaum.spring.rest.employees;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@JacksonXmlRootElement(localName = "employee")
public class Employee {
    @NotBlank
    private String code;

    @NotBlank
    @JacksonXmlProperty(localName = "first_name")
    private String firstName;

    @NotBlank
    @JacksonXmlProperty(localName = "last_name")
    private String lastName;

    @PastOrPresent
    @JacksonXmlProperty(localName = "birth_date")
    private LocalDate birthDate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
