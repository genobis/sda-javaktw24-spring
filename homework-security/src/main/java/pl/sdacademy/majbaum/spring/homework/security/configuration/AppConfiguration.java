package pl.sdacademy.majbaum.spring.homework.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.server.csrf.CsrfToken;
import org.springframework.web.servlet.ModelAndView;
import pl.sdacademy.majbaum.spring.homework.security.configuration.security.UserData;

import java.time.Clock;

@Configuration
public class AppConfiguration {

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }
}
