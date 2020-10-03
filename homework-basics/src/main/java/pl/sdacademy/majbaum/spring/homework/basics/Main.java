package pl.sdacademy.majbaum.spring.homework.basics;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Random;

@SpringBootApplication
public class Main {

    //@SpringBootApplication to jednocześnie konfiguracja, więc możemy tutaj zadeklarować metody fabryczne komponentów

    //Użyty w dwóch komponentach
    @Bean
    public Random random() {
        return new Random();
    }

    //Random brany z kontekstu, definicja powyżej
    @Bean
    public Foo1 foo1(Random random) {
        return new Foo1(random.nextInt());
    }

    //Random brany z kontekstu, definicja powyżej; scope prototype zgodnie z treścią zadania
    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public Foo2 foo2(Random random) {
        return new Foo2(random.nextInt());
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
