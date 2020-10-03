package pl.sdacademy.majbaum.spring.homework.basics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class HomeworkRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(HomeworkRunner.class);
    private final Bar1 bar1;
    private final Bar2 bar2;

    public HomeworkRunner(Bar1 bar1, Bar2 bar2) {
        this.bar1 = bar1;
        this.bar2 = bar2;
    }

    @Override
    public void run(String... args) {
        logger.info("bar1.r1={}", bar1.getR1()); //singleton/singleton, stała wartość z zagnieżdżonego singletona (Foo1)
        logger.info("bar1.r2={}", bar1.getR2()); //singleton/prototype, stała wartość z raz wstrzykniętego prototype
        logger.info("bar2.r1={}", bar2.getR1()); //prototype/singleton, stała wartość z zagnieżdżonego singletona (Foo1)
        logger.info("bar2.r2={}", bar2.getR2()); //prototype/prototype, wartość generowana przy każdym wstrzyknięciu
    }
}
