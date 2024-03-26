package cn.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;


//@Component
//@ConditionalOnProperty(prefix = "service", name = "name", havingValue = "service1")
public class HelloImpl implements IHello {
    @Autowired
    HelloService helloService;

    @Override
    public String sayHello() {
        return helloService.sayHello();
    }
}
