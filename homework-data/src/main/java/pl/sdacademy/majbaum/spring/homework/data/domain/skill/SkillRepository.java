package pl.sdacademy.majbaum.spring.homework.data.domain.skill;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.majbaum.spring.homework.data.model.Skill;

public interface SkillRepository extends JpaRepository<Skill,String> { }
