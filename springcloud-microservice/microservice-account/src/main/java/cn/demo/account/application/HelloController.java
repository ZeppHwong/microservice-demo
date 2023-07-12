package cn.demo.account.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;

@RestController
public class HelloController {
    @Value("${server.port}")
    private int port;
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
    @RequestMapping("/timeout")
    public String timeout(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "this is correct response";
    }

    @RequestMapping("/ribbon")
    public String ribbon() throws UnknownHostException {
        return "ribbon:" + port;
    }
}
