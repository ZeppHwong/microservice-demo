package cn.demo.starter;

import org.springframework.stereotype.Component;

@Component
public class SmsDomain {
    public void sendOver(){
        System.out.println("SmsDomain.sendOver");
    }
}
