package pl.sdacademy.majbaum.spring.boot.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class HelloSpringRunner implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(HelloSpringRunner.class);
    private final Bar bar;

    public HelloSpringRunner(Bar bar) {
        this.bar = bar;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info(bar.hello());
    }
}
