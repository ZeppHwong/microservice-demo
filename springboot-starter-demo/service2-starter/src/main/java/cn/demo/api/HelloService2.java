package cn.demo.api;


import org.springframework.stereotype.Component;

@Component
public class HelloService2 implements IHelloService {
    public String sayHello() {
        return "I'm service 2...";
    }
}
