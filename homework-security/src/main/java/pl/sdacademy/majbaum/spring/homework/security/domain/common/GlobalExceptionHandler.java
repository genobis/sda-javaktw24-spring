package pl.sdacademy.majbaum.spring.homework.security.domain.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import pl.sdacademy.majbaum.spring.homework.security.configuration.security.UserData;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private final UserData userData;

    public GlobalExceptionHandler(UserData userData) {
        this.userData = userData;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAnyException(Exception e) {
        return handle(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ModelAndView handleAnyException(AccessDeniedException e) {
        return handle(e, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ModelAndView handleResponseStatusException(ResponseStatusException e) {
        return handle(e, e.getStatus());
    }

    public ModelAndView handle(HttpStatus httpStatus) {
        return handle(null, httpStatus);
    }

    private ModelAndView handle(Exception e, HttpStatus httpStatus) {
        ModelAndView modelAndView = new ModelAndView()
                .addObject("user", userData)
                .addObject("title", "Błąd " + httpStatus.value());

        if (e != null) {
            if (httpStatus.is4xxClientError()) {
                modelAndView.addObject("exception-message", e.getMessage());
            } else {
                LOGGER.error(e.getMessage(), e);
            }
        }

        modelAndView.setStatus(httpStatus);
        modelAndView.setViewName("exception");
        return modelAndView;
    }
}
