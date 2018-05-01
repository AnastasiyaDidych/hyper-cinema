package com.softserve.edu.hypercinema.service;

import com.softserve.edu.hypercinema.dto.OrderDto;
import com.softserve.edu.hypercinema.entity.OrderEntity;
import com.softserve.edu.hypercinema.entity.UserEntity;
import com.softserve.edu.hypercinema.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


public interface OrderService {


    void createOrder(OrderEntity orderEntity);

    List<OrderEntity> getOrders();

    OrderEntity getOrder(Long id);

    void updateOrder(OrderEntity orderEntity);

    void deleteOrder(Long id);

    void deleteOrder(OrderEntity orderEntity);

    void createOrder(OrderDto orderDto);
}
