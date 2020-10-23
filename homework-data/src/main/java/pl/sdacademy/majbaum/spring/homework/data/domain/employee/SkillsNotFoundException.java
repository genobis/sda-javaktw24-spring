package pl.sdacademy.majbaum.spring.homework.data.domain.employee;

import java.util.Collections;
import java.util.Set;

public class SkillsNotFoundException extends RuntimeException {
    private final Set<String> skillsNotFound;

    public SkillsNotFoundException(Set<String> skillsNotFound) {
        this.skillsNotFound = Collections.unmodifiableSet(skillsNotFound);
    }

    public Set<String> getSkillsNotFound() {
        return skillsNotFound;
    }
}
