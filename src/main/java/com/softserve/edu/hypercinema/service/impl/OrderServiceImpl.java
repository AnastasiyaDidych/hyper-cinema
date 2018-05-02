package com.softserve.edu.hypercinema.service.impl;

import com.softserve.edu.hypercinema.entity.TicketEntity;
import com.softserve.edu.hypercinema.entity.UserEntity;
import com.softserve.edu.hypercinema.exception.OrderNotFoundException;
import com.softserve.edu.hypercinema.entity.OrderEntity;
import com.softserve.edu.hypercinema.repository.OrderRepository;
import com.softserve.edu.hypercinema.service.OrderService;
import com.softserve.edu.hypercinema.service.TicketService;
import com.softserve.edu.hypercinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private static final String ORDER_NOT_FOUND_MESSAGE = "Could not find order with id=";
//    private static final String ORDER_NOT_FOUND_BY_USER_MESSAGE = "Could not find order with user=";

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @Override
    public void createOrder(OrderEntity orderEntity) {
        List<TicketEntity> tickets = orderEntity.getTickets();
        orderEntity.setTickets(Collections.emptyList());
        orderEntity.setUser(getCurrentUser());
        orderRepository.saveAndFlush(orderEntity);

        for (TicketEntity ticket:tickets){
            ticket.setOrder(orderEntity);
            ticketService.createTicket(ticket);
        }
    }

    @Override
    public List<OrderEntity> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public OrderEntity getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(ORDER_NOT_FOUND_MESSAGE + id));
    }

    @Override
    public void updateOrder(OrderEntity orderEntity) {
        orderRepository.save(orderEntity);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void deleteOrder(OrderEntity orderEntity) {
        orderRepository.delete(orderEntity);
    }

    // Oliksiy should fix this method !!!
    private UserEntity getCurrentUser(){
        return userService.getUser(1L);
    }

}
