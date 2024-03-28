package cn.demo.api;


import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnBean(Service2On.class)
public class HelloService2 implements IHelloService {
    public String sayHello() {
        return "I'm service 2...";
    }
}
