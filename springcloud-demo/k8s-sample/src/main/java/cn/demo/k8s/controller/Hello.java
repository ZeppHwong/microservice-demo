package cn.demo.k8s.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
    @Value("${greeting.message:default}")
    private String greeting;

    @Value("${farewell.message:default}")
    private String farewell;

    @GetMapping("/hello")
    public String hello(){
        return "hello k8s";
    }

    @GetMapping("/greeting")
    public String greeting(){
        return greeting;
    }

    @GetMapping("/farewell")
    public String farewell(){
        return farewell;
    }
}
