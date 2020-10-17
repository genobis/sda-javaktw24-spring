package pl.sdacademy.majbaum.spring.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class Main {
    @Bean
    public Random random() {
        return new Random();
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
