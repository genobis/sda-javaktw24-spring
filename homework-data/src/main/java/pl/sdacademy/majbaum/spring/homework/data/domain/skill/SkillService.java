package pl.sdacademy.majbaum.spring.homework.data.domain.skill;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sdacademy.majbaum.spring.homework.data.model.Skill;

import java.util.Optional;

@Service
public class SkillService {
    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Transactional
    public Optional<Skill> createSkill(Skill skill) {
        //Jeśli istnieje - zwraca pusty Optional
        if (skillRepository.existsById(skill.getName())) {
            return Optional.empty();
        }

        //W przeciwnym razie - zapisuje i zwraca
        skillRepository.save(skill);
        return Optional.of(skill);
    }

    public Page<Skill> getSkills(Pageable pageable) {
        return skillRepository.findAll(pageable);
    }
}
