package pl.sdacademy.majbaum.spring.homework.data.domain.skill;

import pl.sdacademy.majbaum.spring.homework.data.model.Skill;

//Mapowanie między typem DTO a klasą encyjną i vice versa
//Zwykle używa się w tym celu bibliotek takich jak np. MapStruct
public class SkillMapper {
    public static Skill map(SkillDto skillDto) {
        final Skill skill = new Skill();
        skill.setName(skillDto.getName());
        skill.setDescription(skillDto.getDescription());
        return skill;
    }

    public static SkillDto map(Skill skill) {
        return new SkillDto(
            skill.getName(),
            skill.getDescription()
        );
    }
}
