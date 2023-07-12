package cn.demo.account.controller;


import cn.demo.account.feign.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderServiceController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/order")
    public String helloOrder(){
       return orderService.hello();
    }
}
