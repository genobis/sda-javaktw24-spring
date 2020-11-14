package pl.sdacademy.majbaum.spring.homework.security.configuration.security;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserDataHandlerInterceptor extends HandlerInterceptorAdapter {
    private final UserData userData;

    public UserDataHandlerInterceptor(UserData userData) {
        this.userData = userData;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView
    ) {
        if (modelAndView != null) {
            modelAndView.addObject("user", userData);
        }
    }
}
