package pl.sdacademy.majbaum.spring.springexceptions;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Controller
@Validated
@RequestMapping("/")
public class ValidatingController {
    @GetMapping
    public String takeValue(
            @RequestParam(required = false) @NotNull @Max(50) @Validated
            final Integer value,
            final Model model
    ) {
        model.addAttribute("value", value);
        return "ok";
    }
}
