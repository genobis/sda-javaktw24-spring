package pl.sdacademy.majbaum.spring.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Map;

//Obsługa modelu na kilka różnych sposobów
@Controller
public class HelloJsp {

    @GetMapping("/hello-jsp-1")
    public String helloJsp(Model model) {
        model.addAttribute("firstName", "Tomek");
        model.addAttribute("lastName", "Majbaum");
        model.addAttribute(
                "birthDate",
                LocalDate.of(1983, 12, 13)
        );

        return "hello.jsp";
    }

    @GetMapping("/hello-jsp-2")
    public String helloJsp(Map<String,Object> model) {
        model.put("firstName", "Tomek");
        model.put("lastName", "Majbaum");
        model.put(
                "birthDate",
                LocalDate.of(1983, 12, 13)
        );

        return "hello.jsp";
    }

    @GetMapping("/hello-jsp-3")
    public ModelAndView helloJsp() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hello.jsp");

        final ModelMap model = modelAndView.getModelMap();
        model.put("firstName", "Tomek");
        model.put("lastName", "Majbaum");
        model.put(
                "birthDate",
                LocalDate.of(1983, 12, 13)
        );

        return modelAndView;
    }
}
