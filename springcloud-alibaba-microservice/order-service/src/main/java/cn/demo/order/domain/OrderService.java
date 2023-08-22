package cn.demo.order.domain;

import cn.demo.order.feign.StorageService;
import cn.demo.order.infra.mapper.OrderMapper;
import cn.demo.order.infra.valueobject.Order;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    StorageService storageService;

    @GlobalTransactional
    @Transactional(rollbackFor = Exception.class)
    public void placeOrder(String userId, String commodityCode, Integer count) {
        BigDecimal orderMoney = new BigDecimal(count).multiply(new BigDecimal(5));
        Order order = new Order();
        order.setUserId(userId);
        order.setCommodityCode(commodityCode);
        order.setCount(count);
        order.setMoney(orderMoney);
        orderMapper.placeOrder(order);
        storageService.reduceStorage(commodityCode, count);
    }
}
