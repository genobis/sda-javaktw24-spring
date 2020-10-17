package pl.sdacademy.majbaum.spring.proxy;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Random;

@Component
//@RequestScope
/*@Scope(
        value = BeanDefinition.SCOPE_PROTOTYPE,
        proxyMode = ScopedProxyMode.TARGET_CLASS
)*/

//równoznaczne z @RequestScope
@Scope(
        value = WebApplicationContext.SCOPE_REQUEST,
        proxyMode = ScopedProxyMode.TARGET_CLASS
)
public class Bar {
    private final int number;

    public Bar(Random random) {
        number = random.nextInt();
    }

    public int getNumber() {
        return number;
    }
}
