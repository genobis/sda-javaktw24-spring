package pl.sdacademy.majbaum.spring.homework.security.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.sdacademy.majbaum.spring.homework.security.configuration.security.UserData;
import pl.sdacademy.majbaum.spring.homework.security.configuration.security.UserDataHandlerInterceptor;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    private final UserData userData;

    public WebConfiguration(UserData userData) {
        this.userData = userData;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserDataHandlerInterceptor(userData));
    }
}
