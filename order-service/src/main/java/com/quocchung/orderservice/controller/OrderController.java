package com.quocchung.orderservice.controller;
import com.quocchung.orderservice.model.dto.CreateOrderRequest;
import com.quocchung.orderservice.model.dto.OrderCreateResponse;
import com.quocchung.orderservice.model.entity.Order;
import com.quocchung.orderservice.repository.OrderRepository;
import com.quocchung.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderRepository orderRepository;
  private final OrderService orderService;

  @PostMapping
  public OrderCreateResponse createOrder(@RequestBody CreateOrderRequest order) {
    return orderService.createOrder(order) ;
  }

  @GetMapping
  public List<Order> getAllOrders() {
    return orderRepository.findAll();
  }

}