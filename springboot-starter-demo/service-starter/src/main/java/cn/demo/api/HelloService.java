package cn.demo.api;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

@Component
//@ConditionalOnBean(Service1On.class)
@ConditionalOnServiceEnabled
public class HelloService implements IHelloService {
    public String sayHello() {
        return "I'm service 1...";
    }
}
