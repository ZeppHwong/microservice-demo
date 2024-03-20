package cn.spring.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"cn.spring", "cn.demo.starter"})
public class LearnApplication {


    public static void main(String[] args) {
        SpringApplication.run(LearnApplication.class, args);
    }
}
