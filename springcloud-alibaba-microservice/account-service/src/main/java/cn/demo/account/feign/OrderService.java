package cn.demo.account.feign;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ali-order-service", path = "/ali-order", fallback = OrderFallbackService.class)
@LoadBalancerClient(name = "ali-order-service")
public interface OrderService {
    @GetMapping(value = "/hello")
    String hello();
}
