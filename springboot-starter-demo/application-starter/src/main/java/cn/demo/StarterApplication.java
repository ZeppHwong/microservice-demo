package cn.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"cn.demo"})
public class StarterApplication {
  public static void main(String[] args) {
    SpringApplication.run(StarterApplication.class, args);
  }
}