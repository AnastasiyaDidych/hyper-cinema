package com.softserve.edu.hypercinema.service;

import com.softserve.edu.hypercinema.entity.OrderEntity;
import org.springframework.security.core.Authentication;
import java.security.Principal;
import java.util.List;


public interface OrderService {


    List<OrderEntity> getOrders(Principal principal);

    List<OrderEntity> getOrders(Authentication authentication);

    OrderEntity getOrder(Long id, Authentication authentication);

    void updateOrder(OrderEntity orderEntity);

    void deleteOrder(Long id);

    void deleteOrder(OrderEntity orderEntity);

    void createOrder(OrderEntity orderEntity, Principal principal);

    List<OrderEntity> getAllOrders ();

}
