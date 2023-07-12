package cn.demo.account.feign;

import org.springframework.stereotype.Component;

@Component
public class OrderFallbackService implements OrderService {
    @Override
    public String hello() {
        return "request time out";
    }
}
