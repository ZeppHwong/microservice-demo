package cn.demo.controller;

import org.springframework.stereotype.Service;

@Service
public class HelloOwnService {
  public void hello() {
    System.out.println("HelloService.hello");
  }
}