package com.softserve.edu.hypercinema.controller;

import com.softserve.edu.hypercinema.converter.OrderConverter;
import com.softserve.edu.hypercinema.converter.PaymentConverter;
import com.softserve.edu.hypercinema.dto.OrderDto;
import com.softserve.edu.hypercinema.dto.PaymentDto;
import com.softserve.edu.hypercinema.service.OrderService;
import com.softserve.edu.hypercinema.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderConverter orderConvertor;

    @Autowired
    private PaymentConverter paymentConverter;

    @Autowired
    private PaymentService paymentService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUserOrder(@RequestBody OrderDto order, Principal principal) {
        orderService.createOrder(orderConvertor.convertToEntity(order), principal);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping
    public void updateOrder(@RequestBody OrderDto order) {
        orderService.updateOrder(orderConvertor.convertToEntity(order));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable Long id, Authentication authentication) {
        return orderConvertor.convertToDto(orderService.getOrder(id, authentication));

    }

    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }


    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping
    public void deleteOrder(@RequestBody OrderDto order) {
        orderService.deleteOrder(orderConvertor.convertToEntity(order));
    }


    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<OrderDto> getListOrders(Authentication authentication) {
        return orderConvertor.convertToDto(orderService.getOrders(authentication));
    }


    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/payments/{id}")
    public PaymentDto getUserPaymentInfo(@PathVariable Long id, Authentication authentication) {
        return paymentConverter.convertToDto(paymentService.getPayment(id, authentication));

    }


    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/payments")
    public List<PaymentDto> getListPayments(Authentication authentication) {
        return paymentConverter.convertToDto(paymentService.getPayments(authentication));
    }


    // VR

    @GetMapping("/all")
    public List<OrderDto> getAllOrders() {
        return orderConvertor.convertToDto(orderService.getAllOrders());
    }

}
