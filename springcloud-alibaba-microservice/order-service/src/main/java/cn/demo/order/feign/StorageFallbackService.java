package cn.demo.order.feign;

import org.springframework.stereotype.Component;

@Component
public class StorageFallbackService implements StorageService {

    @Override
    public String reduceStorage(String commodityCode, Integer count) {
        return  "reduceStorage fallback";
    }

}
