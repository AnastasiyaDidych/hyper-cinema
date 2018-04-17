package com.softserve.edu.hypercinema.controller;

import com.softserve.edu.hypercinema.convertor.OrderConvertor;
import com.softserve.edu.hypercinema.dto.OrderDto;
import com.softserve.edu.hypercinema.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderConvertor orderConvertor;

    @PostMapping
    public void createOrder(@RequestBody OrderDto order) {
        orderService.createOrder(orderConvertor.convertToEntity(order));
    }

    @PutMapping
    public void updateOrder(@RequestBody OrderDto order) {
        orderService.updateOrder(orderConvertor.convertToEntity(order));
    }

    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable Integer id) {
        return orderConvertor.convertToDto(orderService.selectOrderById(id));

    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
    }

    @GetMapping
    public List<OrderDto> getListOrders() {
        return orderConvertor.convertToDto(orderService.selectAllOrders());
    }
}
