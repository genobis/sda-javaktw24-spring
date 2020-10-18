package pl.sdacademy.majbaum.spring.security;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.concurrent.atomic.AtomicLong;

@Component
@SessionScope
public class Foo {
    private final AtomicLong atomicLong = new AtomicLong();

    public long getVal() {
        return atomicLong.incrementAndGet();
    }
}
