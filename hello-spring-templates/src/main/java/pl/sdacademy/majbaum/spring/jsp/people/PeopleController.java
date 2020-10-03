package pl.sdacademy.majbaum.spring.jsp.people;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RequestMapping("/people")
@Controller
public class PeopleController {
    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    //Używa widoku people.mustache
    @GetMapping
    public ModelAndView getPeople() {
        final ModelAndView mav = new ModelAndView();
        mav.setViewName("people");
        mav.getModel().put("people", peopleService.getPeople());
        return mav;
    }

    //Dane z formularza można obsłużyć na kilka sposobów

//    @PostMapping
//    public ModelAndView addPerson(@ModelAttribute Person person) {
//        peopleService.addPerson(person);
//        return getPeople();
//    }

//    @PostMapping
//    public ModelAndView addPerson(@RequestParam Map<String,String> formData) {
//        final Person person = new Person();
//        person.setFirstName(formData.get("firstName"));
//        person.setLastName(formData.get("lastName"));
//
//        peopleService.addPerson(person);
//        return getPeople();
//    }

    @PostMapping
    public ModelAndView addPerson(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName
    ) {
        final Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);

        peopleService.addPerson(person);

        //Obsługę widoku zapewnia logika endpointu [GET] /people
        return getPeople();
    }


}
