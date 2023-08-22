package cn.demo.order.controller;

import cn.demo.order.domain.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping("/placeOrder")
    public String placeOrder(){
        orderService.placeOrder("1", "product-1", 1);
        return "success";
    }
}
