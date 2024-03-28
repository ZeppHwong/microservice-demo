package cn.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnBean(Service2On.class)
public class HelloImpl2 implements IHello {
    @Autowired
    HelloService2 service;

    @Override
    public String sayHello() {
        return service.sayHello();
    }
}
