package cn.demo.order.infra.mapper;

import cn.demo.order.infra.valueobject.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderMapper {
    int placeOrder(Order order);
}
