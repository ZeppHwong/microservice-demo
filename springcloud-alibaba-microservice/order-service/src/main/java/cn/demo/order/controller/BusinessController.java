package cn.demo.order.controller;

import cn.demo.order.feign.StorageService;
import cn.demo.order.infra.mapper.OrderMapper;
import cn.demo.order.infra.valueobject.Order;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessController {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    StorageService storageService;

    @PostMapping("purchase")
    public String purchase(@RequestParam("userId") String userId,
                         @RequestParam("commodityCode") String commodityCode,
                         @RequestParam("count") int orderCount) throws Exception {
        purchaseService(userId,commodityCode, orderCount);
        return "success";
    }

    @Transactional
    @GlobalTransactional
    public void purchaseService(String userId, String commodityCode,int orderCount) throws Exception {
        Order order = new Order();
        order.setUserId(userId);
        order.setCount(orderCount);
        order.setCommodityCode(commodityCode);
        orderMapper.placeOrder(order);
        storageService.reduceStorage(commodityCode, orderCount);
        throw new Exception("模拟异常");
    }
}
