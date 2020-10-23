package pl.sdacademy.majbaum.spring.homework.data.domain.employee;

import org.springframework.util.CollectionUtils;
import pl.sdacademy.majbaum.spring.homework.data.model.Employee;
import pl.sdacademy.majbaum.spring.homework.data.model.Skill;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

//Mapowanie między typem DTO a klasą encyjną i vice versa
//Zwykle używa się w tym celu bibliotek takich jak np. MapStruct
public class EmployeeMapper {
    public static Employee map(EmployeeDto employeeDto) {
        final Set<Skill> skills;
        if (CollectionUtils.isEmpty(employeeDto.getSkills())) {
            skills = null;
        }
        else {
            skills = employeeDto.getSkills().stream()
                    .map(EmployeeMapper::nameToSkill)
                    .collect(Collectors.toUnmodifiableSet());
        }

        final Employee employee = new Employee();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setBirthDate(employeeDto.getBirthDate());
        employee.setSkills(skills);
        return employee;
    }

    public static EmployeeDto mapWithSkills(Employee employee) {
        return map(employee, true);
    }

    public static EmployeeDto mapWithoutSkills(Employee employee) {
        return map(employee, false);
    }

    private static EmployeeDto map(Employee employee, boolean includeSkills) {
        final List<String> skills;
        if (includeSkills) {
            skills = Optional.ofNullable(employee.getSkills()).stream()
                    .flatMap(Collection::stream)
                    .map(Skill::getName)
                    .sorted()
                    .collect(Collectors.toUnmodifiableList());
        }
        else {
            skills = null;
        }

        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getBirthDate(),
                skills
        );
    }

    private static Skill nameToSkill(String name) {
        final Skill skill = new Skill();
        skill.setName(name);
        return skill;
    }
}
