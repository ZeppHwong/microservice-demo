package cn.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;


@Component
//@ConditionalOnBean(Service1On.class)
@ConditionalOnServiceEnabled
public class HelloImpl implements IHello {
    @Autowired
    HelloService helloService;

    @Override
    public String sayHello() {
        return helloService.sayHello();
    }
}
