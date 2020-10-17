package pl.sdacademy.spring.advanced;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;

//@Component
public class AppStarterQualifier implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(AppStarterQualifier.class);
    private final Foo foo;
    private final Foo otherFoo;

    public AppStarterQualifier(
        Foo foo,
        @Qualifier("differentFoo") Foo otherFoo
    ) {
        this.foo = foo;
        this.otherFoo = otherFoo;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("foo: {}", foo.getValue());
        logger.info("otherFoo: {}", otherFoo.getValue());
    }
}
