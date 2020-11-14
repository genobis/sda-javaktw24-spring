package pl.sdacademy.majbaum.spring.homework.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.annotation.RequestScope;
import pl.sdacademy.majbaum.spring.homework.security.configuration.security.UserAuthorities;
import pl.sdacademy.majbaum.spring.homework.security.configuration.security.UserData;
import pl.sdacademy.majbaum.spring.homework.security.configuration.security.UserDataFactory;

import java.util.Optional;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/articles/add").hasAuthority(UserAuthorities.CAN_ADD_ARTICLE)
                .antMatchers(HttpMethod.POST,"/articles/add").hasAuthority(UserAuthorities.CAN_ADD_ARTICLE)
                .antMatchers(HttpMethod.GET,"/articles/*/edit").hasAuthority(UserAuthorities.CAN_EDIT_OWNED_ARTICLE)
                .antMatchers(HttpMethod.PUT,"/articles/*/edit").hasAuthority(UserAuthorities.CAN_EDIT_OWNED_ARTICLE)
                .antMatchers(HttpMethod.POST, "/articles/*/delete").hasAnyAuthority(UserAuthorities.CAN_DELETE_ANY_ARTICLE, UserAuthorities.CAN_DELETE_OWNED_ARTICLE)
                .antMatchers(HttpMethod.GET, "/articles", "/articles/*").permitAll()
                .antMatchers(HttpMethod.GET,"/", "/public/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .and().logout().logoutSuccessUrl("/");
    }

    @Bean
    @RequestScope
    public UserData userData() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .filter(UserData.class::isInstance)
                .map(UserData.class::cast)
                .orElse(UserDataFactory.ANONYMOUS);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
