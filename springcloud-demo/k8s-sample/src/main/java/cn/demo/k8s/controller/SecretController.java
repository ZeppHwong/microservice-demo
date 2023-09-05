package cn.demo.k8s.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecretController {

    @Value("${data.username}")
    private String username;
    @Value("${data.password}")
    private String password;

    @GetMapping("secret")
    public String secret(){
        System.out.println("SecretController.secret username::"+username);
        System.out.println("SecretController.secret password::"+password);
        return "username: " + username +",password :"+password;
    }
}
