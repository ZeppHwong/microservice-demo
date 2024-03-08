package cn.demo.api;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;


@Component
//@ConditionalOnBean(Service1AutoConfiguration.class)
@ConditionalOnProperty(prefix = "service", name = "name", havingValue = "service1")
public class HelloImpl1 implements IHello {
    @Override
    public String sayHello() {
        return "I'm service 1...";
    }
}
