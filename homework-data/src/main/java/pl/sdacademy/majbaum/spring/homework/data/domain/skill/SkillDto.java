package pl.sdacademy.majbaum.spring.homework.data.domain.skill;

import javax.validation.constraints.NotBlank;

public class SkillDto {
    @NotBlank
    private String name;

    private String description;

    //Potrzebny do deserializacji
    public SkillDto() { }

    public SkillDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
