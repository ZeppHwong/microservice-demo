package cn.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy(value = false)
@ConditionalOnProperty(prefix = "service", name = "name", havingValue = "service2")
public class HelloImpl2 implements IHello {
    @Autowired
    HelloService2 service;

    @Override
    public String sayHello() {
        return service.sayHello();
    }
}
