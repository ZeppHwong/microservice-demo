package cn.demo.order.feign;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ali-storage-service", path = "/ali-storage", fallback = StorageFallbackService.class)
@LoadBalancerClient(name = "ali-storage-service")
public interface StorageService {

    @PostMapping(value = "/reduce/storage")
    String reduceStorage(@RequestParam("commodityCode") String commodityCode
            , @RequestParam("count") Integer count);
}
