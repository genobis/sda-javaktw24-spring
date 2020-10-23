package pl.sdacademy.majbaum.spring.homework.data.domain.skill;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Validated
@RestController
@RequestMapping("/skills")
public class SkillController {
    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping
    public SkillDto createSkill(@RequestBody @Validated SkillDto skillDto) {
        return skillService.createSkill(SkillMapper.map(skillDto)) //Tworzy umiejętność
                .map(SkillMapper::map) //Pełny Optional jeśli udało się utworzyć
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT)); //Jak się nie udało, znaczy, że nazwa zajęta
    }

    @GetMapping
    public Page<SkillDto> createSkill(Pageable pageable) {
        return skillService.getSkills(pageable).map(SkillMapper::map); //Pobiera listę umiejętności i mapuje
    }
}
