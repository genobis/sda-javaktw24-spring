package pl.sdacademy.majbaum.spring.homework.security.domain.common;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/error")
public class ErrorControllerImpl implements ErrorController {
    private final GlobalExceptionHandler globalExceptionHandler;

    public ErrorControllerImpl(GlobalExceptionHandler globalExceptionHandler) {
        this.globalExceptionHandler = globalExceptionHandler;
    }

    @GetMapping
    public ModelAndView handle(HttpServletRequest request) {
        return Optional.ofNullable(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE))
                .filter(Integer.class::isInstance)
                .map(Integer.class::cast)
                .map(HttpStatus::valueOf)
                .map(globalExceptionHandler::handle)
                .orElseThrow();
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
