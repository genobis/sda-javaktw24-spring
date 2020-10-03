package pl.sdacademy.majbaum.jsp;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    //Zastępuje domyślną stronę błędu
    @GetMapping("/error")
    public String error(HttpServletRequest req, Model model) {

        //Obsługa na podstawie przekierowania - dlatego kod błędu jest w ŻĄDANIU
        //Z obiektu requestu można uzyskać m.in. oryginalny kod błędu
        Object status = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        //Model można obsłużyć na różne sposoby, widoku nie musi obsługiwać JSP
        model.addAttribute("status", status);
        return "error.jsp";
    }

    //Wymagane przez ErrorController - nic nie robi
    @Override
    public String getErrorPath() {
        return null;
    }
}
