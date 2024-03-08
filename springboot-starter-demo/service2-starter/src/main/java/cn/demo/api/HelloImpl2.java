package cn.demo.api;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
//@ConditionalOnBean(Service2AutoConfiguration.class)
@ConditionalOnProperty(prefix = "service", name = "name", havingValue = "service2")
public class HelloImpl2 implements IHello {
    @Override
    public String sayHello() {
        return "I'm service 2...";
    }
}
