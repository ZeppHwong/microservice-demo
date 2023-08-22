package cn.demo.order.infra;


import cn.demo.order.infra.mapper.OrderMapper;
import cn.demo.order.infra.valueobject.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class OrderMapperTest {
    @Autowired
    OrderMapper orderMapper;

    @Test
    @DisplayName("新增订单测试")
    public void testPlaceOrder(){
        Order order = new Order();
        order.setUserId("111");
        order.setCommodityCode("111");
        order.setCount(1);
        order.setMoney(new BigDecimal(5));
        int result =  orderMapper.placeOrder(order);
        assertEquals(1, result);
        assertEquals(1, order.getId());
    }
}
