package com.softserve.edu.hypercinema.service;

import com.softserve.edu.hypercinema.entity.OrderEntity;
import java.util.List;


public interface OrderService {


    void createOrder(OrderEntity orderEntity);

    List<OrderEntity> getOrders();

    OrderEntity getOrder(Long id);

    void updateOrder(OrderEntity orderEntity);

    void deleteOrder(Long id);

    void deleteOrder(OrderEntity orderEntity);

}
