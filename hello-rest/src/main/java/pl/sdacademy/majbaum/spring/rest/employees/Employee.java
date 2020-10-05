package pl.sdacademy.majbaum.spring.rest.employees;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

//Adnotacja biblioteki Jackson - jak nazwać XML root element; gdy brak, nazwa od klasy (tutaj: Element)
@JacksonXmlRootElement(localName = "employee")
public class Employee {
    @NotBlank //Nie-null, nie-puste, nie mogą być same białe znaki
    private String code;

    @NotBlank //Nie-null, nie-puste, nie mogą być same białe znaki
    @JacksonXmlProperty(localName = "first_name") //Jak nazwać element zawierający wartość; gdy brak, nazwa od pola (tutaj: firstName)
    private String firstName;

    @NotBlank //Nie-null, nie-puste, nie mogą być same białe znaki
    @JacksonXmlProperty(localName = "last_name") //Jak nazwać element zawierający wartość; gdy brak, nazwa od pola (tutaj: lastName)
    private String lastName;

    @PastOrPresent //Data w przeszłości lub bieżąca. Null DOZWOLONY (gdyby nie był, trzeba dodatkowo @NotNull)
    @JacksonXmlProperty(localName = "birth_date")  //Jak nazwać element zawierający wartość; gdy brak, nazwa od pola (tutaj: birthDate)
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
