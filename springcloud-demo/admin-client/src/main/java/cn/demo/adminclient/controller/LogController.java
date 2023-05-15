package cn.demo.adminclient.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class LogController {
    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @GetMapping("/hello")
    public String hello(){
        String s = "hello, I'm admin Client..." + new Date();
        logger.info("hello controller :{}", s);
        return s;
    }
}
