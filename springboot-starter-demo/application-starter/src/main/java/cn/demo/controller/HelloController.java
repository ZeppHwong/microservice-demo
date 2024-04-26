package cn.demo.controller;

import cn.demo.api.IHello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
  @Autowired
  IHello hello;
  @Autowired
  HelloOwnService helloService;


  @GetMapping("/hello")
  public String hello() {
    helloService.hello();
    return hello.sayHello();
  }

}
