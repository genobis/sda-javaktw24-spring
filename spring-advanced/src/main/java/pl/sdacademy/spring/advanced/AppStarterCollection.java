package pl.sdacademy.spring.advanced;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AppStarterCollection implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(AppStarterCollection.class);
    private final Set<Foo> foos;

    public AppStarterCollection(Set<Foo> foos) {
        this.foos = foos;
    }

    @Override
    public void run(String... args) throws Exception {
        for(final Foo foo : foos) {
            logger.info("{}: {}", foo.toString(), foo.getValue());
        }
    }
}
